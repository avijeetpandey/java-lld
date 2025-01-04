package SOLID.InterfaceSegmentedPrinciple;

interface RestrauntEmployee {
    public void cookFood();
    public void decideMenu();
    public void washDishes();
    public void serveFood();
}

public class Waiter_BadImplementationOfInterfaceSegmentation implements RestrauntEmployee {
    @Override
    public void cookFood() {
        System.out.println("cooking"); // not needed
    }

    @Override
    public void decideMenu() {
        System.out.println("deciding"); // not needed
    }

    @Override
    public void washDishes() {
        System.out.println("washing"); // not needed
    }

    @Override
    public void serveFood() {
        System.out.println("serving"); // needed
    }
}
