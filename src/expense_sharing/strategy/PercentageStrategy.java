package expense_sharing.strategy;

import java.util.List;

import expense_sharing.exceptions.InvalidExpenseException;
import expense_sharing.models.PercentageSplit;
import expense_sharing.models.Split;

public class PercentageStrategy implements SplitStrategy {
    @Override
    public void calculateAndValidateSplits(double totalAmount, List<Split> splits) {
        double totalPercent = 0;
        for(Split split: splits) {
            PercentageSplit pSplit = (PercentageSplit) split;
            totalPercent += pSplit.getPercentage();
            double calulatedAmount = Math.round((totalAmount * pSplit.getPercentage()/100.0 * 100.0)/100.0);
            split.setAmount(calulatedAmount);
        }

        if(Math.round(totalPercent * 100.0)/100.0 != 100.0) {
            throw new InvalidExpenseException("Unable to split invalid percentage share");
        }
    }
}
