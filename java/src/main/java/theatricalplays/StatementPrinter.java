package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
	public String print(Invoice invoice, Map<String, Play> plays) {
		Data data = new Data(invoice.customer, invoice.performances, plays);
        data.enrichPerformances();
        return renderPlainText(data);
    }

    private String renderPlainText(Data data) {
        var result = String.format("Statement for %s\n", data.customer());
        for (var perf : data.performances()) {
            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", perf.play.name, usd(perf.calculateAmount()), perf.audience);
        }

        result += String.format("Amount owed is %s\n", usd(data.totalAmount()));
        result += String.format("You earned %s credits\n", data.totalVolumeCredits());
        return result;
    }

    private static String usd(int amount) {
        String frmt = NumberFormat.getCurrencyInstance(Locale.US).format(amount/100);
        return frmt;
    }
}
