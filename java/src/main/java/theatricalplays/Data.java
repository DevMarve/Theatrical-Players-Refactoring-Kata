package theatricalplays;

import java.util.List;
import java.util.Map;

public record Data(String customer, List<Performance> performances) {

    private static Play getPlay(Performance aPerformance, Map<String, Play> plays) {
        return plays.get(aPerformance.playID);
    }

    public static Data createStatementData(Invoice invoice, Map<String, Play> plays) {
        Data data = new Data(invoice.customer, invoice.performances);
        data.performances().forEach((perf) -> {
            perf.setPlay(getPlay(perf, plays));
            perf.calculateAmount();
            perf.calculateVolumeCredits();
        });
        return data;
    }

    public int totalAmount() {
        var result = 0;
        for (var aPerformance : this.performances) {
            result += aPerformance.amount;
        }
        return result;
    }

    public int totalVolumeCredits() {

        int result = 0;
        for (var aPerformance : this.performances) {
            result += aPerformance.volumeCredits;
        }
        return result;
    }
}
