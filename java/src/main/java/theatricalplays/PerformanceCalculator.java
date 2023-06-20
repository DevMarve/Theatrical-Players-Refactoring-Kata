package theatricalplays;

public class PerformanceCalculator {
    protected final Play play;
    protected final int audience;

    public PerformanceCalculator(Play play, int audience) {
        this.play = play;
        this.audience = audience;
    }

    public static PerformanceCalculator createPerformanceCalculator(Play play, int audience) {
        switch (play.type) {
            case "tragedy": {
                return new TragedyPerformanceCalculator(play, audience);
            } case "comedy": {
                return new ComedyPerformanceCalculator(play, audience);
            }
        }
        return new PerformanceCalculator(play, audience);
    }

    public int amount() {
        int amount = 0;
        switch (play.type) {
            case "tragedy": {
                throw new IllegalCallerException();
            }
            case "comedy": {

                break;
            }
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return amount;
    }

    public int volumeCredits() {
        int volumeCredits;
        volumeCredits = Math.max(audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) volumeCredits += Math.floor(audience / 5);
        return volumeCredits;
    }
}
