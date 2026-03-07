package flight_aggregation_system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import flight_aggregation_system.adapter.FlakyAirlineSupplier;
import flight_aggregation_system.adapter.FlightSupplier;
import flight_aggregation_system.adapter.ReliableAirlineSupplier;
import flight_aggregation_system.model.Flight;
import flight_aggregation_system.model.SearchRequest;
import flight_aggregation_system.service.FlightAggregatorService;

public class FlightAggregatorDriverCode {
    public static void main(String[] args) throws InterruptedException {
        List<FlightSupplier> suppliers = new ArrayList<>(Arrays.asList(new ReliableAirlineSupplier(), new FlakyAirlineSupplier()));
        FlightAggregatorService aggregatorService = new FlightAggregatorService(suppliers);

        SearchRequest request = new SearchRequest("NYC", "LHR", "2026-05-10");

        List<Flight> results = aggregatorService.searchAndAggregate(request);

        for(Flight f: results) {
            System.out.println(f);
        }

        aggregatorService.shutdown();
    }
}
