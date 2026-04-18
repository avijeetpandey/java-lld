package elevator.strategy;

import elevator.model.Elevator;
import elevator.model.Request;
import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}
