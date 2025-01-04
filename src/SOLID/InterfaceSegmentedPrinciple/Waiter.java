package SOLID.InterfaceSegmentedPrinciple;

public class Waiter implements WaiterInterface {
    @Override
    public void serve() {
        System.out.println("Waiter");
    }
}
