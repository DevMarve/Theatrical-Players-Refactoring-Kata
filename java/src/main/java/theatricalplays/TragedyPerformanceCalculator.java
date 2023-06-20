package theatricalplays;

public class TragedyPerformanceCalculator extends PerformanceCalculator {
    public TragedyPerformanceCalculator(Play play, int audience) {
        super(play, audience);
    }

    @Override
    public int amount() {

       int  amount = 40000;
        if (audience > 30) {
            amount += 1000 * (audience - 30);
        }

        return amount;
    }
}
