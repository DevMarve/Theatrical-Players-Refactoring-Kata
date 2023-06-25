package theatricalplays;

public class ComedyCalculator extends PerformanceCalculator {
	@Override
	public int volumeCredit() {
		return super.volumeCredit() + (int) Math.floor(performance.audience / 5);
	}

	public ComedyCalculator(Performance perf) {
		super(perf);
	}

	@Override
	public int amount() {
		int result = 30000;
		if (performance.audience > 20) {
			result += 10000 + 500 * (performance.audience - 20);
		}
		return result + 300 * performance.audience;
	}
}
