package theatricalplays;

public record Data(String customer, java.util.List<Performance> performances, java.util.Map<String, Play> plays) {
	private Play getPlay(Performance perf) {
		return this.plays.get(perf.playID);
	}
	public void enrichPerformances() {
		performances.forEach(perf -> {
			PerformanceCalculator calculator = new PerformanceCalculator(perf);
			perf.setPlay(getPlay(perf));
		});
	}
	public int totalAmount() {
        int result = 0;
        for (var perf : performances) {
            result += perf.amount();
        }
        return result;
    }
	public int totalVolumeCredits() {
        int resul = 0;
        for (var perf : performances) {
            // add volume credits
            resul += perf.volumeCredits();
        }
        return resul;
    }
}
