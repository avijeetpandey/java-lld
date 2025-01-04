package SOLID.LiskovSubstitution;

/**
 * Here we can see that this breaks the functionality of the program
 */
public class AnotherTwoWheeler_Bicycle implements Bike {
    int speed;
    
    @Override
    public void turnOnEngine() {
        throw new AssertionError("no engine found");
    }

    @Override
    public void accelerate() {
        speed += 5;
    }
}
