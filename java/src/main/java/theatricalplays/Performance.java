package theatricalplays;

public class Performance {

    public String playID;
    public int audience;
	public Play play;
    public int amount;
    public int volumeCredit;
    public PerformanceCalculator calculator;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

}
