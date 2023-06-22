package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private Map<String, Play> plays;

    public String print(Invoice invoice, Map<String, Play> plays) {
        this.plays = plays;
        var result = String.format("Statement for %s\n", invoice.customer);

        var totalAmount = 0;

        for (var perf : invoice.performances) {
            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", getPlay(perf).name, usd(amountFor(perf)), perf.audience);
        }

        result += String.format("Amount owed is %s\n", usd(applePie(invoice, totalAmount)));
        result += String.format("You earned %s credits\n", totalVolumeCredits(invoice));
        return result;
    }

    private int applePie(Invoice invoice, int totalAmount) {
        for (var perf : invoice.performances) {
            totalAmount += amountFor(perf);
        }
        return totalAmount;
    }

    private int totalVolumeCredits(Invoice invoice) {
        int resul = 0;
        for (var perf : invoice.performances) {
            // add volume credits
            resul += volumeCreditsFor(perf);
        }
        return resul;
    }

    private static String usd(int amount) {
        String frmt = NumberFormat.getCurrencyInstance(Locale.US).format(amount/100);
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
