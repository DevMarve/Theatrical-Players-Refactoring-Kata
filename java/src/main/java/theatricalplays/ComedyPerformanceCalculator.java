package theatricalplays;

public class ComedyPerformanceCalculator extends PerformanceCalculator {
    public ComedyPerformanceCalculator(Play play, int audience) {
        super(play, audience);
    }

    @Override
    public int amount() {
        int result = 30000;
        if (audience > 20) {
            result += 10000 + 500 * (audience - 20);
        }
        result += 300 * audience;
        return result;
    }

    @Override
    public int volumeCredits() {
        int volumeCredits = super.volumeCredits();
        return volumeCredits += Math.floor(audience / 5);
    }
}
