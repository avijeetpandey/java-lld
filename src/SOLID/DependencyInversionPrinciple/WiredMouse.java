package SOLID.DependencyInversionPrinciple;

public class WiredMouse implements Mouse {
    @Override
    public void track() {
        System.out.println("Tracking mouse");
    }
}
