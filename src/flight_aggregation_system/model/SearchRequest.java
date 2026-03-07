package flight_aggregation_system.model;

public class SearchRequest {
    private final String from;
    private final String to;
    private String date;

    public SearchRequest(String from, String to, String date) {
        this.from = from;
        this.to = to;
        this.date = date;
    }
}
