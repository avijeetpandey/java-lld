package expense_sharing.strategy;

import java.util.List;

import expense_sharing.models.Split;

public interface SplitStrategy {
    void calculateAndValidateSplits(double totalAmount, List<Split> splits);
}
