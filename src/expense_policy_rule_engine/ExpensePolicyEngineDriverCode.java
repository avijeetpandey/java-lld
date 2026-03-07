package expense_policy_rule_engine;

import java.math.BigDecimal;
import java.util.Arrays;

import expense_policy_rule_engine.enums.ExpenseCategory;
import expense_policy_rule_engine.model.Expense;
import expense_policy_rule_engine.rule.BlockListMerchantRule;
import expense_policy_rule_engine.rule.CategoryLimitReviewRule;
import expense_policy_rule_engine.rule.MaxAmountRejectRule;

public class ExpensePolicyEngineDriverCode {
    public static void main(String[] args) {
        ExpensePolicyEngine policyEngine = new ExpensePolicyEngine();

        // dynamic rules
        policyEngine.addRule(new BlockListMerchantRule(Arrays.asList("casino", "luxury")));
        policyEngine.addRule(new MaxAmountRejectRule(new BigDecimal(5000)));
        policyEngine.addRule(new CategoryLimitReviewRule(ExpenseCategory.SOFTWARE, new BigDecimal(2000)));
        policyEngine.addRule(new CategoryLimitReviewRule(ExpenseCategory.MEAL, new BigDecimal(2000)));

        // test cases

        Expense mealExpense = new Expense("mealOne",
                "EMP1",
                "Meal ordered for office party",
                ExpenseCategory.MEAL, new BigDecimal(200),
                "Swiggy");


        System.out.println(policyEngine.evaluateExpense(mealExpense));

        Expense casinoExpense = new Expense("casinoOne",
                "EMP1",
                "Casino reimbursement for team",
                ExpenseCategory.MISC, new BigDecimal(200),
                "casino");

        System.out.println(policyEngine.evaluateExpense(casinoExpense));
    }
}
