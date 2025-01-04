package parkingLot;

import parkingLot.vehicleType.Car;
import parkingLot.vehicleType.MotorCycle;
import parkingLot.vehicleType.Truck;
import parkingLot.vehicleType.Vehicle;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new ParkingLevel(1,100));
        parkingLot.addLevel(new ParkingLevel(2,80));

        Vehicle car = new Car("CAR123");
        Vehicle skoda = new Car("SKODA123");
        Vehicle truck = new Truck("TRUCK123");
        Vehicle motorCycle = new MotorCycle("MOTORCY123");

        // park vehicles
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorCycle);
        parkingLot.parkVehicle(skoda);

        // display availability
        parkingLot.displayAvailability();

        // unpark vehicles
        parkingLot.unparkVehicle(car);

        // display updated availability
        parkingLot.displayAvailability();
    }
}
