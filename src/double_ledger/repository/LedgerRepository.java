package double_ledger.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import double_ledger.model.Ledger;

public class LedgerRepository {
    private final Map<String, Ledger> ledger = new ConcurrentHashMap<>();

    public void save(Ledger ledgerEntry) {
        ledger.put(ledgerEntry.getTransactionId(), ledgerEntry);
    }

    public Ledger get(String transactionId) {
        return ledger.get(transactionId);
    }

    public Map<String, Ledger> getLedger() {
        return ledger;
    }
}
