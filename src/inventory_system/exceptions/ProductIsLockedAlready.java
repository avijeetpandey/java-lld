package exceptions;

public class ProductIsLockedAlready extends RuntimeException {
    public ProductIsLockedAlready(String message) {
        super(message);
    }
}
