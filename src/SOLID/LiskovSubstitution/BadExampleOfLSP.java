package SOLID.LiskovSubstitution;

public class BadExampleOfLSP implements Bike {
    boolean isEngineOn;
    int speed;

    @Override
    public void turnOnEngine() {
        isEngineOn = true;
    }

    @Override
    public void accelerate() {
        speed += 10;
    }
}

