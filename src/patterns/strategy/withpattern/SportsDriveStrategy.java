package patterns.strategy.withpattern;

public class SportsDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("sports drive");
    }
}
