package theatricalplays;

public class Performance {

    public String playID;
    public int audience;
	public Play play;
    public PerformanceCalculator calculator;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public int calculateAmount() {
        return calculator.amount();
    }

    public int calculateVolumeCredit() {
        return calculator.volumeCredit();
    }
}
