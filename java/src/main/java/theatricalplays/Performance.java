package theatricalplays;

public class Performance {

    public String playID;
    public int audience;
	public Play play;
    public int amount;
    public int volumeCredit;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

	public void setPlay(Play play) {
		this.play = play;
	}
	public int amount() {
        return amount;
    }
    public int volumeCredits() {
        return volumeCredit;
    }
}
