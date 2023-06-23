package theatricalplays;

public record Data(String customer, java.util.List<Performance> performances, java.util.Map<String, Play> plays) {
	private Play getPlay(Performance perf) {
		return this.plays.get(perf.playID);
	}
	public void enrichPerformances() {
		performances.forEach(perf -> {
			PerformanceCalculator calculator = createPerformanceCalculator(perf, getPlay(perf));
			perf.play = getPlay(perf);
			perf.amount = calculator.amount();
			perf.volumeCredit = calculator.volumeCredit();
		});
	}

	private static PerformanceCalculator createPerformanceCalculator(Performance perf, Play play) {
		switch (play.type) {
			case "tragedy":
				return new TragedyCalculator(perf);
			case "comedy":
				return new ComedyCalculator(perf);
		}
		return new PerformanceCalculator(perf);
	}

	public int totalAmount() {
        int result = 0;
        for (var perf : performances) {
			result += perf.amount;
        }
        return result;
    }
	public int totalVolumeCredits() {
        int resul = 0;
        for (var perf : performances) {
            // add volume credits
			resul += perf.volumeCredit;
        }
        return resul;
    }
}
