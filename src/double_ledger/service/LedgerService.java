package double_ledger.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import double_ledger.enums.TransactionType;
import double_ledger.exceptions.InvalidTransactionException;
import double_ledger.model.Account;
import double_ledger.model.Ledger;
import double_ledger.repository.AccountRepository;
import double_ledger.repository.LedgerRepository;

public class LedgerService {
    private final LedgerRepository ledgerRepository;
    private final AccountRepository accountRepository;

    public LedgerService(LedgerRepository ledgerRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
    }

    public String transfer(String toAccountId, String fromAccountId, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("amount to transfer should be greater than zero");
        }

        if(toAccountId.equals(fromAccountId)) {
            throw new InvalidTransactionException("cannout transfer to same account");
        }

        Account toAccount = accountRepository.get(toAccountId);
        Account fromAccount = accountRepository.get(fromAccountId);

        if(toAccount == null || fromAccount == null) {
            throw new InvalidTransactionException("Account not found");
        }

        Account firstLock = fromAccountId.compareTo(toAccountId) < 0 ? fromAccount : toAccount;
        Account secondLock = fromAccountId.compareTo(toAccountId) < 0 ? toAccount : fromAccount;

        // acquire lock
        firstLock.getLock().lock();
        secondLock.getLock().lock();

        String transactionId = UUID.randomUUID().toString();

        try {
            fromAccount.debit(amount);
            toAccount.credit(amount);

            Ledger debitEntry = new Ledger(transactionId, fromAccountId, amount, TransactionType.DEBIT);
            Ledger creditEntry = new Ledger(transactionId, toAccountId, amount, TransactionType.CREDIT);

            ledgerRepository.save(creditEntry);
            ledgerRepository.save(debitEntry);

            return transactionId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            firstLock.getLock().unlock();
            secondLock.getLock().unlock();
        }

        return transactionId;
    }

    public void printLedger() {
      Map<String, Ledger> ledger =  ledgerRepository.getLedger();
      for(Ledger ledgerEntry : ledger.values()) {
        System.out.println(ledgerEntry);
      }
    }
}
