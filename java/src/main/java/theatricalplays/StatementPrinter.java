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
        var result = String.format("Statement for %s\n", invoice.customer);

        for (var aPerformance : invoice.performances) {
            result += String.format("  %s: %s (%s seats)\n", getPlay(aPerformance).name, usd(amountFor(aPerformance)), aPerformance.audience);
        }

        result += String.format("Amount owed is %s\n", usd(totalAmount(invoice)));
        result += String.format("You earned %s credits\n", totalVolumeCredits(invoice));
        return result;
    }

    private int totalAmount(Invoice invoice) {
        var result = 0;
        for (var aPerformance : invoice.performances) {
            result += amountFor(aPerformance);
        }
        return result;
    }

    private int totalVolumeCredits(Invoice invoice) {

        int result = 0;
        for (var aPerformance : invoice.performances) {
            result += volumeCreditsFor(aPerformance);
        }
        return result;
    }

    private int amountFor(Performance perf) {
        int result;
        switch (getPlay(perf).type) {
            case "tragedy":
                result = 40000;
                if (perf.audience > 30) {
                    result += 1000 * (perf.audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.audience > 20) {
                    result += 10000 + 500 * (perf.audience - 20);
                }
                result += 300 * perf.audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result;
        result = Math.max(aPerformance.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(getPlay(aPerformance).type)) result += Math.floor(aPerformance.audience / 5);
        return result;
    }

    private Play getPlay(Performance aPerformance) {
        return this.plays.get(aPerformance.playID);
    }

}
