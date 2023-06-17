package theatricalplays;

public class Performance {

    public String playID;
    public int audience;

    public Play play;

    public void setPlay(Play play) {
        this.play = play;
    }

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }
}
