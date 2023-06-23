package theatricalplays;

public class PerformanceCalculator {
	private final Performance performance;

	public PerformanceCalculator(Performance aPerformance) {
		this.performance = aPerformance;
	}

	public int amount() {
		int result;
		switch (performance.play.type) {
			case "tragedy":
				result = 40000;
				if (performance.audience > 30) {
					result += 1000 * (performance.audience - 30);
				}
				break;
			case "comedy":
				result = 30000;
				if (performance.audience > 20) {
					result += 10000 + 500 * (performance.audience - 20);
				}
				result += 300 * performance.audience;
				break;
			default:
				throw new Error("unknown type: ${play.type}");
		}
		return result;
	}
}
