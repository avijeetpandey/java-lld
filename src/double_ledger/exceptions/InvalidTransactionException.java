package double_ledger.exceptions;

public class InvalidTransactionException extends RuntimeException {
    private String message;

    public InvalidTransactionException(String message) {
        super(message);
    }
}
