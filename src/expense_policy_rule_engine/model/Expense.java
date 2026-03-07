package expense_policy_rule_engine.model;

import java.math.BigDecimal;

import expense_policy_rule_engine.enums.ExpenseCategory;

public class Expense {
    private final String id;
    private final String employeId;
    private final String description;
    private final BigDecimal amount;
    private final String merchant;
    private final ExpenseCategory category;

    public Expense(String id, String employeId, String description, ExpenseCategory category, BigDecimal amount,
            String merchant) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.employeId = employeId;
        this.amount = amount;
        this.merchant = merchant;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getEmployeeId() {
        return employeId;
    }

    public ExpenseCategory getExpenseCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchantName() {
        return merchant;
    }
}
