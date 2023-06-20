package theatricalplays;

public class PerformanceCalculator {
    private final Play play;
    private final int audience;

    public PerformanceCalculator(Play play, int audience) {
        this.play = play;
        this.audience = audience;
    }

    public int amount() {
        int amount;
        switch (play.type) {
            case "tragedy": {
                amount = 40000;
                if (audience > 30) {
                    amount += 1000 * (audience - 30);
                }
                break;
            }
            case "comedy": {
                amount = 30000;
                if (audience > 20) {
                    amount += 10000 + 500 * (audience - 20);
                }
                amount += 300 * audience;
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
