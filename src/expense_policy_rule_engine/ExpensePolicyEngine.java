package expense_policy_rule_engine;

import java.util.ArrayList;
import java.util.List;

import expense_policy_rule_engine.enums.ExpenseStatus;
import expense_policy_rule_engine.model.EvaluationResult;
import expense_policy_rule_engine.model.Expense;
import expense_policy_rule_engine.rule.ExpensePolicyRule;

public class ExpensePolicyEngine {
    private final List<ExpensePolicyRule> rules = new ArrayList<>();

    public void addRule(ExpensePolicyRule rule) {
        rules.add(rule);
    }

    public EvaluationResult evaluateExpense(Expense expense) {
        EvaluationResult finalResult = new EvaluationResult(ExpenseStatus.APPROVED, null);
        for(ExpensePolicyRule rule: rules) {
            EvaluationResult ruleResult = rule.evaluate(expense);
            finalResult.merge(ruleResult);

            if(finalResult.getStatus() == ExpenseStatus.REJECTED) {
                break;
            }
        }

        if(finalResult.getStatus() == ExpenseStatus.APPROVED && finalResult.getReasons().isEmpty()) {
            finalResult.getReasons().add("Auto approved by the policy engine");
        }

        return finalResult;
    }
}
