package flight_aggregation_system.adapter;

import java.util.List;

import flight_aggregation_system.model.Flight;
import flight_aggregation_system.model.SearchRequest;

public interface FlightSupplier {
    String getSupplierName();
    List<Flight> fetchFlights(SearchRequest searchRequest);
}