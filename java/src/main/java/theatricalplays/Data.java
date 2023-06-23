package theatricalplays;

public record Data(String customer, java.util.List<Performance> performances, java.util.Map<String, Play> plays) {
	public void enrichPerformances() {
		performances.forEach(perf -> {
			perf.setPlay(plays.get(perf.playID));
		});
	}
}
