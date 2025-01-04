package parkingLot;

import parkingLot.vehicleType.Vehicle;
import parkingLot.vehicleType.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private final int floor;
    private final List<ParkingSpot> parkingSpots;

    public ParkingLevel(int floor, int numberOfSpots) {
        this.floor = floor;
        parkingSpots = new ArrayList<>(numberOfSpots);

        // assigning the parking spots in the ratio 50:40:10 for bikes, cars and trucks
        double spotsForBikes = 0.50;
        double spotsForCars = 0.40;

        int numberOfBikes = (int) (numberOfSpots *  spotsForBikes);
        int numberOfCars = (int) (numberOfSpots *  spotsForCars);

        for(int i = 1; i <= numberOfBikes; i++) {
            parkingSpots.add(new ParkingSpot(i, VehicleType.MOTORCYCLE));
        }

        for(int i=numberOfBikes; i <= numberOfCars + numberOfBikes; i++) {
            parkingSpots.add(new ParkingSpot(i, VehicleType.CAR));
        }

        for(int i=numberOfCars + numberOfBikes + 1; i <= numberOfSpots; i++) {
            parkingSpots.add(new ParkingSpot(i, VehicleType.TRUCK));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for(ParkingSpot parkingSpot : parkingSpots) {
            if(parkingSpot.isAvailable() && parkingSpot.getVehicleType() == vehicle.getVehicleType()) {
                parkingSpot.park(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for(ParkingSpot parkingSpot : parkingSpots) {
            if(!parkingSpot.isAvailable() && parkingSpot.getParkedVehicle().equals(vehicle)) {
                parkingSpot.unparkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.println("Level "+ floor + " Availability: ");
        for(ParkingSpot parkingSpot : parkingSpots) {
            System.out.println("Spot "+
                    parkingSpot.getSpotNumber() +
                    " : " +
                    (parkingSpot.isAvailable() ? "Avaialable for" : "Occupied by" + " " + parkingSpot.getVehicleType()));
        }
    }
}
