package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private Map<String, Play> plays;
    private Invoice invoice;

    private static String usd(int currency) {
        NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
        return usd.format(currency / 100);
    }

    public String print(Invoice invoice, Map<String, Play> plays) {
        this.plays = plays;
        this.invoice = invoice;
        Data data = new Data(this.invoice.customer, this.invoice.performances);
        data.performances().forEach((perf) -> {
            perf.setPlay(getPlay(perf));
            perf.calculateAmount();
            perf.setVolumeCredits(volumeCreditsFor(perf));
                });
        return renderAsPlainText(data);
    }

    private String renderAsPlainText(Data data) {
        var result = String.format("Statement for %s\n", data.customer());

        for (var aPerformance : data.performances()) {
            result += String.format("  %s: %s (%s seats)\n", aPerformance.play.name, usd(aPerformance.amount), aPerformance.audience);
        }

        result += String.format("Amount owed is %s\n", usd(data.totalAmount()));
        result += String.format("You earned %s credits\n", data.totalVolumeCredits());
        return result;
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result;
        result = Math.max(aPerformance.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(aPerformance.play.type)) result += Math.floor(aPerformance.audience / 5);
        return result;
    }

    private Play getPlay(Performance aPerformance) {
        return this.plays.get(aPerformance.playID);
    }

}
