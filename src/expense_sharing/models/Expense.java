package expense_sharing.models;

import java.util.List;

public class Expense {
    private final String expenseId;
    private final String name;
    private final double totalAmount;
    private final String paidBy;
    private final List<Split> splits;

    public Expense(String expenseId, String name, double totalAmount, String paidBy, List<Split> splits) {
        this.expenseId = expenseId;
        this.totalAmount = totalAmount;
        this.name = name;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getName() {
        return name;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }
}
