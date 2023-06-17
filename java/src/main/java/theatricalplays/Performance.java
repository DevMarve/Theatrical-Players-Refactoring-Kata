package theatricalplays;

public class Performance {

    public String playID;
    public int audience;

    public Play play;
    public int amount;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}
