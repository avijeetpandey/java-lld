package double_ledger.exceptions;

public class AccountAlreadyExisitsException extends RuntimeException {
    private String message;

    public AccountAlreadyExisitsException(String message) {
        super(message);
    }
}
