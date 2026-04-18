package elevator.core;

import elevator.model.Elevator;
import elevator.model.Request;
import elevator.strategy.ElevatorSelectionStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorController {
    private List<Elevator> elevators;
    private ElevatorSelectionStrategy selectionStrategy;
    private ExecutorService executorService;

    public ElevatorController(int numElevators, ElevatorSelectionStrategy selectionStrategy) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(i + 1));
        }
        this.selectionStrategy = selectionStrategy;
        this.executorService = Executors.newFixedThreadPool(numElevators);
        
        // Start a thread for each elevator to process its requests
        for (Elevator elevator : elevators) {
            executorService.submit(() -> {
                while (true) {
                    elevator.processRequests();
                    try {
                        // Simulate time between floor movements
                        Thread.sleep(1000); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }

    public void requestElevator(Request request) {
        Elevator selectedElevator = selectionStrategy.selectElevator(elevators, request);
        if (selectedElevator != null) {
            // Add both the pickup and destination floors to the elevator's queue
            selectedElevator.addRequest(request.getCurrentFloor());
            selectedElevator.addRequest(request.getDesiredFloor());
            System.out.println("Request from floor " + request.getCurrentFloor() + " to " + request.getDesiredFloor() + " assigned to Elevator " + selectedElevator.getId());
        } else {
            System.out.println("No available elevators for request from floor " + request.getCurrentFloor() + " to " + request.getDesiredFloor());
        }
    }
    
    public void shutdown() {
        executorService.shutdownNow();
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
