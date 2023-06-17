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
        var totalAmount = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        for (var aPerformance : invoice.performances) {
            // add volume credits
            totalAmount += amountFor(aPerformance);
            // print line for this order
            result += printLine(aPerformance);
        }

        var volumeCredits = 0;
        volumeCredits = totalVolumeCredits(invoice);

        result += String.format("Amount owed is %s\n", usd(totalAmount));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private int totalVolumeCredits(Invoice invoice) {

        int volumeCredits = 0;
        for (var aPerformance : invoice.performances) {
            volumeCredits += volumeCreditsFor(aPerformance);
        }
        return volumeCredits;
    }

    private String printLine(Performance aPerformance) {
        return String.format("  %s: %s (%s seats)\n", getPlay(aPerformance).name, usd(amountFor(aPerformance)), aPerformance.audience);
    }

    private int amountFor(Performance perf) {
        int thisAmount;
        switch (getPlay(perf).type) {
            case "tragedy":
                thisAmount = 40000;
                if (perf.audience > 30) {
                    thisAmount += 1000 * (perf.audience - 30);
                }
                break;
            case "comedy":
                thisAmount = 30000;
                if (perf.audience > 20) {
                    thisAmount += 10000 + 500 * (perf.audience - 20);
                }
                thisAmount += 300 * perf.audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return thisAmount;
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
