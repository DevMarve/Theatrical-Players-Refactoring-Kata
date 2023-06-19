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

    public int amountFor() {
        int result;
        switch (play.type) {
            case "tragedy":
                result = 40000;
                if (audience > 30) {
                    result += 1000 * (audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (audience > 20) {
                    result += 10000 + 500 * (audience - 20);
                }
                result += 300 * audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }
}
