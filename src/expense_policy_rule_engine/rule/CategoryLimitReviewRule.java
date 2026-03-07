package expense_policy_rule_engine.rule;

import java.math.BigDecimal;

import expense_policy_rule_engine.enums.ExpenseCategory;
import expense_policy_rule_engine.enums.ExpenseStatus;
import expense_policy_rule_engine.model.EvaluationResult;
import expense_policy_rule_engine.model.Expense;

public class CategoryLimitReviewRule implements ExpensePolicyRule {

    private final ExpenseCategory category;
    private final BigDecimal softLimit;

    public CategoryLimitReviewRule(ExpenseCategory category, BigDecimal softLimit) {
        this.category = category;
        this.softLimit = softLimit;
    }

    @Override
    public EvaluationResult evaluate(Expense expense) {
        if (expense.getExpenseCategory() == category && expense.getAmount().compareTo(softLimit) > 0) {
            return new EvaluationResult(ExpenseStatus.MANUAL_REVIEW, "Amount exceeds soft cap needs manual review");
        }

        return new EvaluationResult(ExpenseStatus.APPROVED, null);
    }
}
