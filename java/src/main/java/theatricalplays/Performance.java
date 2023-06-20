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

    public void setPlay(Play play) {
        this.play = play;
    }

    public void calculateAmount() {
        amount = new PerformanceCalculator(play, audience).amount();
    }

    public void calculateVolumeCredits() {
        volumeCredits = new PerformanceCalculator(play, audience).volumeCredits();
    }
}
