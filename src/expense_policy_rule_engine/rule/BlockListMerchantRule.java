package expense_policy_rule_engine.rule;

import java.util.List;

import expense_policy_rule_engine.enums.ExpenseStatus;
import expense_policy_rule_engine.model.EvaluationResult;
import expense_policy_rule_engine.model.Expense;

public class BlockListMerchantRule implements ExpensePolicyRule {

    private final List<String> blockListMerchants;

    public BlockListMerchantRule(List<String> blockListMerchants) {
        this.blockListMerchants = blockListMerchants;
    }

    @Override
    public EvaluationResult evaluate(Expense expense) {
        if (blockListMerchants.contains(expense.getMerchantName().toLowerCase())) {
            return new EvaluationResult(ExpenseStatus.REJECTED,
                    "Merchant" + expense.getMerchantName() + " is blacklisted");
        }

        return new EvaluationResult(ExpenseStatus.APPROVED, null);
    }
}
