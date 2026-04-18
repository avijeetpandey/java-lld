# Elevator Management System

This project is a Java-based simulation of an elevator management system. It's designed with scalability and extensibility in mind, leveraging key design patterns to ensure the code is clean, modular, and easy to maintain.

## Design Patterns Used

### 1. Strategy Pattern

- **Purpose**: To define a family of algorithms, encapsulate each one, and make them interchangeable. This lets the algorithm vary independently from the clients that use it.
- **Implementation**: The `ElevatorSelectionStrategy` interface defines the contract for selecting an elevator. Different strategies can be implemented to cater to various needs:
    - `NearestElevatorStrategy`: Selects the elevator closest to the requested floor. This is simple and effective for minimizing wait times in many scenarios.
    - `OptimalElevatorStrategy`: Aims to distribute the load more evenly among elevators by selecting the one with the fewest pending requests. This can improve overall system throughput.
- **Benefits**: We can easily add new selection strategies (e.g., a strategy for VIP requests, or one that considers energy consumption) without modifying the core `ElevatorController`. The desired strategy is injected into the controller at runtime.

### 2. Command Pattern

- **Purpose**: To encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.
- **Implementation**: The `Request` class acts as a command object. It encapsulates all the information needed to service a request: the current floor, the desired floor, and the direction.
- **Benefits**: By treating requests as objects, we can easily queue them, pass them to different methods, and even store a history of requests if needed.

### 3. Singleton Pattern (Implicit)

- **Purpose**: To ensure that a class has only one instance and provide a global point of access to it.
- **Implementation**: The `ElevatorController` acts as a singleton in this system. While not explicitly enforced with a private constructor and static instance, its role is to be the single point of coordination for all elevator operations.
- **Benefits**: Centralizes the management of elevators and requests, preventing conflicts and ensuring consistent behavior across the system.

## Folder Structure

The code is organized into the following packages to promote modularity and separation of concerns:

- `elevator.core`: Contains the main `ElevatorController`, which is the brain of the system.
- `elevator.model`: Holds the Plain Old Java Objects (POJOs) that represent the core entities of our system, such as `Elevator`, `Request`, `Direction`, and `Status`.
- `elevator.strategy`: Includes the different `ElevatorSelectionStrategy` implementations. This is where the logic for choosing the best elevator for a given request resides.
- `elevator`: The root package, which contains the `Main` class to run the simulation.

```
src/
└── elevator/
    ├── core/
    │   └── ElevatorController.java
    ├── model/
    │   ├── Direction.java
    │   ├── Elevator.java
    │   ├── Request.java
    │   └── Status.java
    ├── strategy/
    │   ├── ElevatorSelectionStrategy.java
    │   ├── NearestElevatorStrategy.java
    │   └── OptimalElevatorStrategy.java
    └── Main.java
```

## How to Run

1. **Compile the code**:
   ```bash
   javac -d . src/elevator/model/*.java src/elevator/strategy/*.java src/elevator/core/*.java src/elevator/Main.java
   ```
2. **Run the simulation**:
   ```bash
   java elevator.Main
   ```

## Example Usage

The `Main.java` file provides a simple demonstration of how to use the `ElevatorController`:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Elevator System...");

        // Create a controller with 3 elevators and the NearestElevatorStrategy
        ElevatorController controller = new ElevatorController(3, new NearestElevatorStrategy());

        // Simulate some user requests
        controller.requestElevator(new Request(0, 5, Direction.UP));
        
        try { Thread.sleep(2000); } catch(InterruptedException e) {}
        
        controller.requestElevator(new Request(2, 8, Direction.UP));
        controller.requestElevator(new Request(10, 1, Direction.DOWN));

        // Allow time for the system to process requests
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Shutting down Elevator System.");
        controller.shutdown();
    }
}
```

### Switching Strategies

To use a different elevator selection strategy, simply pass a different strategy object to the `ElevatorController`'s constructor:

```java
// Use the OptimalElevatorStrategy instead
ElevatorController controller = new ElevatorController(3, new OptimalElevatorStrategy());
```

This demonstrates the power of the Strategy Pattern, allowing for easy changes in behavior without altering the core logic of the controller.
