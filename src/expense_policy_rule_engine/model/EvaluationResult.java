package expense_policy_rule_engine.model;

import java.util.ArrayList;
import java.util.List;

import expense_policy_rule_engine.enums.ExpenseStatus;

public class EvaluationResult {
    private ExpenseStatus status;
    private final List<String> reasons;

    public EvaluationResult(ExpenseStatus status, String reason) {
        this.status = status;
        this.reasons = new ArrayList<>();
        if (this.reasons != null && !reasons.isEmpty()) {
            reasons.add(reason);
        }
    }

    public void merge(EvaluationResult other) {
        if (other.status == ExpenseStatus.REJECTED) {
            this.status = ExpenseStatus.REJECTED;
        } else if (other.status == ExpenseStatus.MANUAL_REVIEW && this.status != ExpenseStatus.REJECTED) {
            this.status = ExpenseStatus.MANUAL_REVIEW;
        }
        this.reasons.addAll(other.getReasons());
    }

    public List<String> getReasons() {
        return reasons;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Status: " + status + " | Reasons: " + String.join(" AND ", reasons);
    }
}
