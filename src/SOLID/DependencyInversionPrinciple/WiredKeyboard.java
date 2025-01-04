package SOLID.DependencyInversionPrinciple;

public class WiredKeyboard implements Keyboard {
    @Override
    public void type() {
        System.out.println("Wired Keyboard");
    }
}
