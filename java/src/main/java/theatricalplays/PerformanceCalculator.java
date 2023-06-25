package theatricalplays;

public interface PerformanceCalculator {
    default int amount() {
        throw new Error("unknown type: ${play.type}");
    }

    default int volumeCredit() {
        int result = Math.max(getAudience() - 30, 0);
        return result;
    }

    int getAudience();
}
