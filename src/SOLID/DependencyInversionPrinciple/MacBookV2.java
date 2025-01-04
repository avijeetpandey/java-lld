package SOLID.DependencyInversionPrinciple;

/**
 * This class follows the dependency inversion principle while creating macbook, this implementation is much clear and concise.
 */
public class MacBookV2 {
    private Keyboard keyboard;
    private Mouse mouse;

    public MacBookV2(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void bootSystem() {
        keyboard.type();
        mouse.track();
    }
}
