package theatricalplays;

public record Data(String customer, java.util.List<Performance> performances, java.util.Map<String, Play> plays) {
	private Play getPlay(Performance perf) {
		return this.plays.get(perf.playID);
	}
	public void enrichPerformances() {
		performances.forEach(perf -> {
			perf.setPlay(getPlay(perf));
		});
	}
}
