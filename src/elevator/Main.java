package elevator;

import elevator.core.ElevatorController;
import elevator.model.Direction;
import elevator.model.Request;
import elevator.strategy.NearestElevatorStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Elevator System...");

        ElevatorController controller = new ElevatorController(3, new NearestElevatorStrategy());

        // Simulate some requests
        controller.requestElevator(new Request(0, 5, Direction.UP));
        
        try {
            // Wait to let the first elevator start moving before issuing more requests
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Main thread interrupted while waiting.");
        }
        
        controller.requestElevator(new Request(2, 8, Direction.UP));
        controller.requestElevator(new Request(10, 1, Direction.DOWN));

        // Let the system run for a while to process all requests
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Main thread interrupted while processing requests.");
        }

        System.out.println("Shutting down Elevator System.");
        controller.shutdown();
    }
}
