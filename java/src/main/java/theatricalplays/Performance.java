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

	public void setPlay(Play play) {
		this.play = play;
	}
	public int amount() {
        return amount;
    }
    public int volumeCredits() {
        int result = Math.max(audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) result += Math.floor(audience / 5);
        return result;
    }
}
