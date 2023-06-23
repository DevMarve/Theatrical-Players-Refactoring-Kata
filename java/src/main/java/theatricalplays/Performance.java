package theatricalplays;

public class Performance {

    public String playID;
    public int audience;
	private Play play;

	public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

	public void setPlay(Play play) {
		this.play = play;
	}
}
