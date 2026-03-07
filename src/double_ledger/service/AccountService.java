package double_ledger.service;

import java.math.BigDecimal;

import double_ledger.exceptions.AccountAlreadyExisitsException;
import double_ledger.model.Account;
import double_ledger.repository.AccountRepository;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(String id, String name, BigDecimal initialBalance) {
        Account account = new Account(id, name, initialBalance);
        if(accountRepository.get(id) != null) {
            throw new AccountAlreadyExisitsException("Account already exists");
        }

        accountRepository.save(account);
    }
}
