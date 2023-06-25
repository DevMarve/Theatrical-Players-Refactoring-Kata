package theatricalplays;

public class TragedyCalculator implements PerformanceCalculator {
	private final Performance performance;

	public TragedyCalculator(Performance perf) {
		this.performance = perf;
	}

	@Override
	public int amount() {
		int result = 40000;
		if (performance.audience > 30) {
			result += 1000 * (performance.audience - 30);
		}
		return result;
	}

	@Override
	public int getAudience() {
		return performance.audience;
	}
}
