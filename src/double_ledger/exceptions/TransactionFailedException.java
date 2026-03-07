package double_ledger.exceptions;

public class TransactionFailedException extends RuntimeException {
    private String message;

    public TransactionFailedException(String message) {
        super(message);
    }
}
