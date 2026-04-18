package elevator.strategy;

import elevator.model.Elevator;
import elevator.model.Request;
import elevator.model.Status;
import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getStatus() == Status.MAINTENANCE) {
                continue;
            }

            int distance = Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor());

            // Prioritize idle elevators or those moving towards the request
            boolean isEligible = elevator.getStatus() == Status.IDLE ||
                                 (elevator.getDirection() == request.getDirection() &&
                                  ((request.getDirection() == elevator.model.Direction.UP && elevator.getCurrentFloor() <= request.getCurrentFloor()) ||
                                   (request.getDirection() == elevator.model.Direction.DOWN && elevator.getCurrentFloor() >= request.getCurrentFloor())));

            if (isEligible && distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        // Fallback: If no "eligible" elevator is found, just find the absolute closest one.
        if (bestElevator == null) {
            for (Elevator elevator : elevators) {
                 if (elevator.getStatus() == Status.MAINTENANCE) continue;
                int distance = Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }
        
        return bestElevator;
    }
}
