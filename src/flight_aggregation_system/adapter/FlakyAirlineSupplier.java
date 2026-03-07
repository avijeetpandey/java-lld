package flight_aggregation_system.adapter;

import java.util.Arrays;
import java.util.List;

import flight_aggregation_system.model.Flight;
import flight_aggregation_system.model.SearchRequest;

public class FlakyAirlineSupplier implements FlightSupplier {

    @Override
    public List<Flight> fetchFlights(SearchRequest searchRequest) {
        sleep(5000);
        return Arrays.asList(new Flight("IG100", "indido", 450, 360),
                new Flight("IG200", "Indigo", 456, 100));
    }

    @Override
    public String getSupplierName() {
        return "Indigo airline";
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }
}
