package SOLID.DependencyInversionPrinciple;

// Here lets say i want to create a macbook with keyboard and mouse , so if i want to enhance this to wireless implementation it wont be that easy.
public class Macbook {
    private final WiredKeyboard wiredKeyboard;
    private final WiredMouse wiredMouse;

    public Macbook() {
        this.wiredKeyboard = new WiredKeyboard();
        this.wiredMouse = new WiredMouse();
    }

    public void bootSystem() {
        wiredKeyboard.type();
        wiredMouse.track();
    }
}
