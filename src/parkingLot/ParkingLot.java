package parkingLot;

import parkingLot.vehicleType.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingLevel> levels;

    private ParkingLot() {
        levels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if(instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addLevel(ParkingLevel level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for(ParkingLevel level : levels) {
            if(level.parkVehicle(vehicle)) {
                System.out.println(" vehicle parked successfully");
                return true;
            }
        }

        System.out.println("Could not park vehicle");
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for(ParkingLevel level : levels) {
            if(level.unparkVehicle(vehicle)) {
                System.out.println(" vehicle unparked successfully");
                return true;
            }
        }
        System.out.println("Could not unpark vehicle");
        return false;
    }

    public void displayAvailability() {
        for(ParkingLevel level : levels) {
            level.displayAvailability();
        }
    }
}
