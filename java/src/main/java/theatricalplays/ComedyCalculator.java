package theatricalplays;

public class ComedyCalculator implements PerformanceCalculator {
    private final Performance performance;

    public ComedyCalculator(Performance perf) {
        this.performance = perf;
    }

    @Override
    public int volumeCredit() {
        return PerformanceCalculator.super.volumeCredit() + (int) Math.floor(performance.audience / 5);
    }

    @Override
    public int getAudience() {
        return performance.audience;
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
