package exceptions;

public class OutOfStockException extends RuntimeException {
    private String message;
    
    public OutOfStockException(String message) {
        super(message);
    }
}
