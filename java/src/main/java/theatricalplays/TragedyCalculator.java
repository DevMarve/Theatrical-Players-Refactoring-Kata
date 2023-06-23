package theatricalplays;

public class TragedyCalculator extends PerformanceCalculator {
	public TragedyCalculator(Performance perf) {
		super(perf);
	}

	@Override
	public int amount() {
		int result = 40000;
		if (performance.audience > 30) {
			result += 1000 * (performance.audience - 30);
		}
		return result;
	}
}
