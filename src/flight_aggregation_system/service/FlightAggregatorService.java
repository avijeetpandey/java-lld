package flight_aggregation_system.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import flight_aggregation_system.adapter.FlightSupplier;
import flight_aggregation_system.model.Flight;
import flight_aggregation_system.model.SearchRequest;

public class FlightAggregatorService {
    private final List<FlightSupplier> suppliers;
    private final ExecutorService networkExecutor;
    private final int NETWORK_TIMEOUT_SECONDS = 2;

    public FlightAggregatorService(List<FlightSupplier> suppliers) {
        this.suppliers = suppliers;
        this.networkExecutor = Executors.newFixedThreadPool(50);
    }

    public List<Flight> searchAndAggregate(SearchRequest request) throws InterruptedException {
        List<Callable<List<Flight>>> tasks = new ArrayList<>();

        for (FlightSupplier supplier : suppliers) {
            tasks.add(() -> supplier.fetchFlights(request));
        }

        // execute all the services at once
        List<Future<List<Flight>>> futures = networkExecutor.invokeAll(tasks, NETWORK_TIMEOUT_SECONDS,
                TimeUnit.SECONDS);

        List<Flight> allFlights = new ArrayList<>();
        for (Future<List<Flight>> future : futures) {
            try {
                if (!future.isCancelled()) {
                    allFlights.addAll(future.get());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // sort the flights based on the price
        allFlights.sort(Comparator.comparing(Flight::getPrice));

        return allFlights;
    }

    public void shutdown() {
        networkExecutor.shutdown();
    }
}
