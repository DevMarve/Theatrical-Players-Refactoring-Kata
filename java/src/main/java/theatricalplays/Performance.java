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
	public int amountFor() {
        int result;
        switch (play.type) {
            case "tragedy":
                result = 40000;
                if (audience > 30) {
                    result += 1000 * (audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (audience > 20) {
                    result += 10000 + 500 * (audience - 20);
                }
                result += 300 * audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }
    public int volumeCreditsFor(Performance aPerformance) {
        int result = Math.max(aPerformance.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) result += Math.floor(aPerformance.audience / 5);
        return result;
    }
}
