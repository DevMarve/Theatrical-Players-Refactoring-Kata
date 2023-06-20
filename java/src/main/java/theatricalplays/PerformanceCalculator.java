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
            }
            case "comedy": {
                return new ComedyPerformanceCalculator(play, audience);
            }
        }
        throw new Error("unknown type: ${play.type}");
    }

    public int amount() { throw new UnsupportedOperationException();}

    public int volumeCredits() {
        int volumeCredits;
        volumeCredits = Math.max(audience - 30, 0);
        // add extra credit for every ten comedy attendees
        return volumeCredits;
    }
}
