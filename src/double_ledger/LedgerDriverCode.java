package double_ledger;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import double_ledger.repository.AccountRepository;
import double_ledger.repository.LedgerRepository;
import double_ledger.service.AccountService;
import double_ledger.service.LedgerService;

public class LedgerDriverCode {
    public static void main(String[] args) throws InterruptedException {
        AccountRepository accountRepository = new AccountRepository();
        LedgerRepository ledgerRepository = new LedgerRepository();
        AccountService accountService = new AccountService(accountRepository);
        LedgerService ledgerService = new LedgerService(ledgerRepository, accountRepository);

        // create account
        accountService.createAccount("accountOne", "Aman", new BigDecimal(3000));
        accountService.createAccount("accountTwo", "Akshat", new BigDecimal(3000));

        // multiple executions
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        System.out.println("Starting heavy concurrent transfers");

        // initate transfer
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executorService.submit(() -> {
                try {
                    // Even threads transfer A -> B, Odd threads transfer B -> A
                    if (index % 2 == 0) {
                        ledgerService.transfer("accountOne", "accountTwo", new BigDecimal(3000));
                    } else {
                        ledgerService.transfer("accountTwo", "accountOne", new BigDecimal(3000));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // print ledger
        ledgerService.printLedger();
    }
}
