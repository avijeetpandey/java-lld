package SOLID.LiskovSubstitution;

public class Bicycle implements Bike {
    int speed;

    @Override
    public void accelerate() {
        speed += 5;
    }

    @Override
    public void turnOnEngine() {
        throw new AssertionError("engine not found");
    }
}
