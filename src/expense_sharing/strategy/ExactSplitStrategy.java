package expense_sharing.strategy;

import java.util.List;

import expense_sharing.exceptions.InvalidExpenseException;
import expense_sharing.models.Split;

public class ExactSplitStrategy implements SplitStrategy {
    @Override
    public void calculateAndValidateSplits(double totalAmount, List<Split> splits) {
        double sum = 0;
        for(Split split: splits) {
            sum += split.getAmount();
        }

        if(sum != totalAmount) {
            throw new InvalidExpenseException("Exact amounts do not add up");
        }
    }
}
