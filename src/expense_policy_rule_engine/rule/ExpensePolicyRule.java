package expense_policy_rule_engine.rule;

import expense_policy_rule_engine.model.EvaluationResult;
import expense_policy_rule_engine.model.Expense;

public interface ExpensePolicyRule {
    EvaluationResult evaluate(Expense expense);
}
