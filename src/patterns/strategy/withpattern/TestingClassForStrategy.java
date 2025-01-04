package patterns.strategy.withpattern;

public class TestingClassForStrategy {
    public static void main(String[] args) {
        Vehicle sportsVehicle = new SportsVehicle();
        sportsVehicle.drive();

        Vehicle normalVehicle = new PassengerVehicle();
        normalVehicle.drive();
    }
}
