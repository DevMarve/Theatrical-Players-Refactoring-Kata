package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private Map<String, Play> plays;

    public String print(Invoice invoice, Map<String, Play> plays) {
        this.plays = plays;
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        for (var perf : invoice.performances) {

            // add volume credits
            volumeCredits += volumeCreditsFor(perf);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", getPlay(perf).name, usd().format(amountFor(perf) / 100), perf.audience);
            totalAmount += amountFor(perf);
        }
        result += String.format("Amount owed is %s\n", usd().format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private static NumberFormat usd() {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt;
    }

    private Play getPlay(Performance perf) {
        return this.plays.get(perf.playID);
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result = Math.max(aPerformance.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(getPlay(aPerformance).type)) result += Math.floor(aPerformance.audience / 5);
        return result;
    }

    private int amountFor(Performance aPerformance) {
        int result;
        switch (getPlay(aPerformance).type) {
            case "tragedy":
                result = 40000;
                if (aPerformance.audience > 30) {
                    result += 1000 * (aPerformance.audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.audience > 20) {
                    result += 10000 + 500 * (aPerformance.audience - 20);
                }
                result += 300 * aPerformance.audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

}
