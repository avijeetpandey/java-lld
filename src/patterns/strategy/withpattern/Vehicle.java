package patterns.strategy.withpattern;

class Vehicle {
    DriveStrategy driveObject;

    Vehicle(DriveStrategy driveObject) {
        this.driveObject = driveObject;
    }

    public void drive() {
        driveObject.drive();
    }
}
