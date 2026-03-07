package double_ledger.exceptions;

public class InsufficientFundsException extends RuntimeException {
    private String message;

    public InsufficientFundsException(String messsage) {
        super(messsage);
    }
}
