package theatricalplays;

public record Data(String customer, java.util.List<Performance> performances, java.util.Map<String, Play> plays) {
	private Play getPlay(Performance perf) {
		return this.plays.get(perf.getPlayID());
	}
	public void enrichPerformances() {
		performances.forEach(perf -> {
			PerformanceCalculator calculator = createPerformanceCalculator(perf, getPlay(perf));
			perf.setPlay(getPlay(perf));
			perf.setCalculator(calculator);
		});
	}

	private static PerformanceCalculator createPerformanceCalculator(Performance perf, Play play) {
		switch (play.getType()) {
			case "tragedy":
				return new TragedyCalculator(perf);
			case "comedy":
				return new ComedyCalculator(perf);
		}
		throw new Error("unknown type: ${play.type}");
	}

	public int totalAmount() {
        int result = 0;
        for (var perf : performances) {
			result += perf.calculateAmount();
        }
        return result;
    }
	public int totalVolumeCredits() {
        int resul = 0;
        for (var perf : performances) {
            // add volume credits
			resul += perf.calculateVolumeCredit();
        }
        return resul;
    }
}
