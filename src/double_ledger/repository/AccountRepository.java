package double_ledger.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import double_ledger.model.Account;

public class AccountRepository {
    private final Map<String, Account> maps = new ConcurrentHashMap<>();

    public void save(Account account) {
        maps.put(account.getAccountId(), account);
    }

    public Account get(String accountId) {
        return maps.get(accountId);
    }
}
