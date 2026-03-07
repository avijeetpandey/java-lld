package double_ledger.model;

import java.math.BigDecimal;
import java.time.Instant;

import double_ledger.enums.TransactionType;

public class Ledger {
    private final String transactionId;
    private final String accountId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final Instant timestamp;

    public Ledger(String transactionId, String accountId, BigDecimal amount, TransactionType type) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.timestamp = Instant.now();
    }

    public String getTransactionId() {
        return transactionId;
    }


    @Override
    public String toString() {
        return "Ledger [transactionId=" + transactionId + ", accountId=" + accountId + ", amount=" + amount + ", type="
                + type + ", timestamp=" + timestamp + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
}
