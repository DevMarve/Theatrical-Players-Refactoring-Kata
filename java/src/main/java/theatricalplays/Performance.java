package theatricalplays;

public class Performance {

    private final String playID;
    private final int audience;
	private Play play;
    private PerformanceCalculator calculator;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public int calculateAmount() {
        return this.calculator.amount();
    }

    public int calculateVolumeCredit() {
        return this.calculator.volumeCredit();
    }

    public String getPlayID() {
        return playID;
    }

    public int getAudience() {
        return audience;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public void setCalculator(PerformanceCalculator calculator) {
        this.calculator = calculator;
    }
}
