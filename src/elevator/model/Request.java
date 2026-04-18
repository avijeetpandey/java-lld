package elevator.model;

public class Request {
    private int currentFloor;
    private int desiredFloor;
    private Direction direction;

    public Request(int currentFloor, int desiredFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.desiredFloor = desiredFloor;
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDesiredFloor() {
        return desiredFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
