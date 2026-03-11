package expense_sharing.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import expense_sharing.exceptions.UserNotFoundException;
import expense_sharing.models.Expense;
import expense_sharing.models.Split;
import expense_sharing.models.User;
import expense_sharing.repository.UserRepository;
import expense_sharing.strategy.SplitStrategy;

public class ExpenseManagerService {
    private final UserRepository userRepository;
    private final List<Expense> expenses = Collections.synchronizedList(new ArrayList<>());

    // ledger
    private final Map<String, ConcurrentHashMap<String, Double>> balanceSheet = new ConcurrentHashMap<>();
    private final AtomicInteger expenseCounter = new AtomicInteger(1);

    public ExpenseManagerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
        balanceSheet.put(user.getId(), new ConcurrentHashMap<>());
    }

    public void addExpense(String name, double totalAmount, String paidBy, List<Split> splits, SplitStrategy strategy) {
        if (userRepository.getUser(paidBy) == null) {
            throw new UserNotFoundException("User not found");
        }

        // split the amounts
        strategy.calculateAndValidateSplits(totalAmount, splits);

        // create a new expense
        Expense expense = new Expense("EXP" + expenseCounter.getAndIncrement(), name, totalAmount, paidBy, splits);
        expenses.add(expense);

        // update balance sheet
        for (Split split : splits) {
            String paidTo = split.getUserId();
            double splitAmount = split.getAmount();
            if (paidBy.equals(paidBy) || totalAmount == 0)
                continue;
            updateBalance(paidTo, paidBy, splitAmount);
        }
    }

    private void updateBalance(String owes, String owed, double amount) {
        balanceSheet.get(owes).compute(owed, (k, val) -> {
            return (double) Math.round(((val == null ? 0 : val) + amount) * 100.0 / 100.0);
        });

        balanceSheet.get(owed).compute(owed, (k, val) -> {
            return (double) Math.round(((val == null ? 0 : val) - amount) * 100.0 / 100.0);
        });
    }
}
