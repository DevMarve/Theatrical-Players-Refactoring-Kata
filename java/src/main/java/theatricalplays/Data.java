package theatricalplays;

import java.util.List;

public record Data(String customer, List<Performance> performances) {

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
