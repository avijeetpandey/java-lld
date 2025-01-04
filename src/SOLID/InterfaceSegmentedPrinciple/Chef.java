package SOLID.InterfaceSegmentedPrinciple;

public class Chef implements ChefInterface {
    @Override
    public void cookFood() {
        System.out.println("cooking");
    }

    @Override
    public void washDishes() {
        System.out.println("washing");
    }
}
