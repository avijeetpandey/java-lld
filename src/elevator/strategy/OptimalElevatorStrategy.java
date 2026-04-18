package elevator.strategy;

import elevator.model.Elevator;
import elevator.model.Request;
import elevator.model.Status;
import java.util.List;

public class OptimalElevatorStrategy implements ElevatorSelectionStrategy {

    // Simple implementation aiming for a more balanced load
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Elevator bestElevator = null;
        int minLoad = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getStatus() == Status.MAINTENANCE) {
                continue;
            }

            int load = elevator.getUpStopsCount() + elevator.getDownStopsCount();
            if (load < minLoad) {
                minLoad = load;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
}
