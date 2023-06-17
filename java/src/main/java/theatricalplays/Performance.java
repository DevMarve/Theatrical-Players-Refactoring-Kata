package theatricalplays;

public class Performance {

    public String playID;
    public int audience;

    public Play play;
    public int amountFor;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public void setAmountFor(int amountFor) {
        this.amountFor = amountFor;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}
