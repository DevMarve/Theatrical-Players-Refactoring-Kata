package theatricalplays;

import java.util.List;
import java.util.Map;

public record Data(Map<String, Play> plays, String customer, List<Performance> performances) {

}
