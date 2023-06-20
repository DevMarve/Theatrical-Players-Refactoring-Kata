package theatricalplays;

public class ComedyPerformanceCalculator extends PerformanceCalculator {
    public ComedyPerformanceCalculator(Play play, int audience) {
        super(play, audience);
    }

    @Override
    public int amount() {
        int amount = 30000;
        if (audience > 20) {
            amount += 10000 + 500 * (audience - 20);
        }
        amount += 300 * audience;
        return amount;
    }

    @Override
    public int volumeCredits() {
        int volumeCredits = super.volumeCredits();

        return volumeCredits += Math.floor(audience / 5);
    }
}
