package flight_aggregation_system.adapter;

import java.util.Arrays;
import java.util.List;

import flight_aggregation_system.model.Flight;
import flight_aggregation_system.model.SearchRequest;

public class ReliableAirlineSupplier implements FlightSupplier {

    @Override
    public List<Flight> fetchFlights(SearchRequest searchRequest) {
        sleep(300);
        return Arrays.asList(new Flight("AI100", "Air India", 500, 300), new Flight("AI200", "Air India", 200, 173));
    }

    @Override
    public String getSupplierName() {
        return "Air india";
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }
}
