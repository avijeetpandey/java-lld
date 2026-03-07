package exceptions;

public class ProductAlreadyAddedException extends RuntimeException {
    private String message;

    public  ProductAlreadyAddedException(String message) {
        super(message);
    }
}
