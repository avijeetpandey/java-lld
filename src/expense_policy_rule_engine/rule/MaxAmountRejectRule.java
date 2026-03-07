package expense_policy_rule_engine.rule;

import java.math.BigDecimal;

import expense_policy_rule_engine.enums.ExpenseStatus;
import expense_policy_rule_engine.model.EvaluationResult;
import expense_policy_rule_engine.model.Expense;

public class MaxAmountRejectRule implements ExpensePolicyRule {
    private final BigDecimal maxAllowed;

    public MaxAmountRejectRule(BigDecimal maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

    @Override
    public EvaluationResult evaluate(Expense expense) {
        if (expense.getAmount().compareTo(maxAllowed) > 0) {
            return new EvaluationResult(ExpenseStatus.REJECTED, "Amount exceeds the max allowed limit");
        }

        return new EvaluationResult(ExpenseStatus.APPROVED, null);
    }
}
