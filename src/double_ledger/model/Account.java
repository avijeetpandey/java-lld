package double_ledger.model;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

import double_ledger.exceptions.InsufficientFundsException;

public class Account {
    private final String id;
    private final String name;
    private BigDecimal balance;
    private final ReentrantLock lock;

    public Account(String id, String name, BigDecimal initialBalance) {
        this.id = id;
        this.name = name;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

    // getters
    public ReentrantLock getLock() {
        return lock;
    }

    public String getAccountId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void debit(BigDecimal debitAmount) {
        if(balance.compareTo(debitAmount) < 0) {
            throw new InsufficientFundsException("Account " + id +" has insufficient funds");
        }

        balance.subtract(debitAmount);
    }

    public void credit(BigDecimal creditAmount) {
        balance.add(creditAmount);
    }
}
