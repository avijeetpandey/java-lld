package elevator.model;

import java.util.PriorityQueue;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private Status status;
    private PriorityQueue<Integer> upStops;
    private PriorityQueue<Integer> downStops;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0; // Starts at ground floor
        this.direction = Direction.IDLE;
        this.status = Status.IDLE;
        
        // Min-heap for UP requests (serves lowest floor first when going up)
        this.upStops = new PriorityQueue<>();
        
        // Max-heap for DOWN requests (serves highest floor first when going down)
        this.downStops = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addRequest(int floor) {
        if (this.direction == Direction.UP) {
            if (floor >= this.currentFloor) {
                upStops.add(floor);
            }
        } else if (this.direction == Direction.DOWN) {
            if (floor <= this.currentFloor) {
                downStops.add(floor);
            }
        } else { // IDLE
            if (floor > this.currentFloor) {
                this.direction = Direction.UP;
                upStops.add(floor);
            } else if (floor < this.currentFloor) {
                this.direction = Direction.DOWN;
                downStops.add(floor);
            }
        }
    }

    public void processRequests() {
        // This method will be driven by the ElevatorController's thread
        if (this.status == Status.MAINTENANCE) return;

        if (this.direction == Direction.UP) {
            if (!upStops.isEmpty()) {
                Integer nextStop = upStops.peek();
                if (this.currentFloor == nextStop) {
                    upStops.poll();
                    System.out.println("Elevator " + id + " stopped at floor " + this.currentFloor);
                } else {
                    System.out.println("Elevator " + id + " moving UP to floor " + (this.currentFloor + 1));
                    this.currentFloor++;
                }
            } else {
                // If no more up stops, check for down stops
                if (!downStops.isEmpty()) {
                    this.direction = Direction.DOWN;
                } else {
                    this.direction = Direction.IDLE;
                    this.status = Status.IDLE;
                }
            }
        } else if (this.direction == Direction.DOWN) {
            if (!downStops.isEmpty()) {
                Integer nextStop = downStops.peek();
                if (this.currentFloor == nextStop) {
                    downStops.poll();
                    System.out.println("Elevator " + id + " stopped at floor " + this.currentFloor);
                } else {
                    System.out.println("Elevator " + id + " moving DOWN to floor " + (this.currentFloor - 1));
                    this.currentFloor--;
                }
            } else {
                // If no more down stops, check for up stops
                if (!upStops.isEmpty()) {
                    this.direction = Direction.UP;
                } else {
                    this.direction = Direction.IDLE;
                    this.status = Status.IDLE;
                }
            }
        } else { // IDLE
            // If idle and there are requests, start moving
            if (!upStops.isEmpty()) {
                this.direction = Direction.UP;
                this.status = Status.MOVING;
            } else if (!downStops.isEmpty()) {
                this.direction = Direction.DOWN;
                this.status = Status.MOVING;
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUpStopsCount() {
        return upStops.size();
    }

    public int getDownStopsCount() {
        return downStops.size();
    }
}
