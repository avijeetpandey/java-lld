package exceptions;

public class ProductAlreadyInInventory extends RuntimeException {
    public ProductAlreadyInInventory(String message) {
        super(message);
    }
}
