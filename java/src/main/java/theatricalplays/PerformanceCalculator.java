package theatricalplays;

public class PerformanceCalculator {
	protected final Performance performance;

	public PerformanceCalculator(Performance aPerformance) {
		this.performance = aPerformance;
	}

	public int amount() {
		int result;
		switch (performance.play.type) {
			case "tragedy":
				throw new RuntimeException("Should be handled by subclass");
			case "comedy":
				throw new RuntimeException("Should be handled by subclass");
			default:
				throw new Error("unknown type: ${play.type}");
		}
	}

	public int volumeCredit() {
		int result = Math.max(performance.audience - 30, 0);
		// add extra credit for every ten comedy attendees
		return result;
	}
}
