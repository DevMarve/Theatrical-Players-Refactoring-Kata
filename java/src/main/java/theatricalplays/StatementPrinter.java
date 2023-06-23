package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private Map<String, Play> plays;
    private Invoice invoice;

    public String print(Invoice invoice, Map<String, Play> plays) {
        this.plays = plays;
        this.invoice = invoice;

        Data data = new Data(invoice.customer, invoice.performances, plays);
        data.enrichPerformances();
        return renderPlainText(data);
    }

    private String renderPlainText(Data data) {
        var result = String.format("Statement for %s\n", data.customer());
        for (var perf : data.performances()) {
            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", perf.play.name, usd(perf.amount()), perf.audience);
        }

        result += String.format("Amount owed is %s\n", usd(totalAmount()));
        result += String.format("You earned %s credits\n", totalVolumeCredits());
        return result;
    }

    private int totalAmount() {
        int result = 0;
        for (var perf : invoice.performances) {
            result += perf.amount();
        }
        return result;
    }

    private int totalVolumeCredits() {
        int resul = 0;
        for (var perf : invoice.performances) {
            // add volume credits
            resul += perf.volumeCredits();
        }
        return resul;
    }

    private static String usd(int amount) {
        String frmt = NumberFormat.getCurrencyInstance(Locale.US).format(amount/100);
        return frmt;
    }
}
