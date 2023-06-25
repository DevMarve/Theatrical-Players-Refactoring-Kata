package theatricalplays;

public class PerformanceCalculator {
    protected final Performance performance;

    public PerformanceCalculator(Performance aPerformance) {
        this.performance = aPerformance;
    }

    public int amount() {
        throw new Error("unknown type: ${play.type}");
    }

    public int volumeCredit() {
        int result = Math.max(performance.audience - 30, 0);
        return result;
    }
}
