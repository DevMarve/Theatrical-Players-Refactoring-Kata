package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private Map<String, Play> plays;

    private static String usd(int currency) {
        NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
        return usd.format(currency / 100);
    }

    public String print(Invoice invoice, Map<String, Play> plays) {
        this.plays = plays;
        Data data = new Data(invoice.customer, invoice.performances);
        data.performances().forEach((perf) -> {
            perf.setPlay(getPlay(perf));
            perf.calculateAmount();
            perf.calculateVolumeCredits();
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

    private Play getPlay(Performance aPerformance) {
        return this.plays.get(aPerformance.playID);
    }

}
