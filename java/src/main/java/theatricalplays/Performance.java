package theatricalplays;

public class Performance {

    public String playID;
    public int audience;

    public Play play;
    public int amount;
    public int volumeCredits;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public void setVolumeCredits(int volumeCredits) {
        this.volumeCredits = volumeCredits;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public void calculateAmount() {
        switch (play.type) {
            case "tragedy":
                amount = 40000;
                if (audience > 30) {
                    amount += 1000 * (audience - 30);
                }
                break;
            case "comedy":
                amount = 30000;
                if (audience > 20) {
                    amount += 10000 + 500 * (audience - 20);
                }
                amount += 300 * audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
    }

    public void volumeCreditsFor() {
        volumeCredits = Math.max(audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) volumeCredits += Math.floor(audience / 5);
    }
}
