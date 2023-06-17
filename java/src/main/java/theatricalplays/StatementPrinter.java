package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var aPerformance : invoice.performances) {
            var play = plays.get(aPerformance.playID);
            var thisAmount = 0;

            thisAmount = amountFor(aPerformance, play);

            // add volume credits
            volumeCredits += volumeFor(aPerformance, play);


            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), aPerformance.audience);
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private static int volumeFor(Performance aPerformance, Play play) {
        int result;
        result = Math.max(aPerformance.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) result += Math.floor(aPerformance.audience / 5);
        return result;
    }

    private static int amountFor(Performance perf, Play play) {
        int thisAmount;
        switch (play.type) {
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

}
