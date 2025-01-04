package SOLID.LiskovSubstitution;

public class MotorCycle implements Bike{
    boolean turnOnEngine;
    int speed;

    @Override
    public void accelerate() {
        speed += 10;
    }

    @Override
    public void turnOnEngine() {
        turnOnEngine = true;
    }
}
