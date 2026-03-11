package expense_sharing.strategy;

import java.util.List;

import expense_sharing.models.Split;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public void calculateAndValidateSplits(double totalAmount, List<Split> splits) {
        // divide all the splits equally among users
        int totalSplits = splits.size();
        double baseSplitAmount = Math.round((totalAmount/totalSplits)*100)/100.0;
        double totalCalculated = 0;

        for(Split split: splits) {
            split.setAmount(baseSplitAmount);
            totalCalculated += baseSplitAmount;
        }

        double difference = Math.round((totalAmount - totalCalculated)*100.0)/100.0;

        // add it to the first person
        Split firstSplit = splits.get(0);
        firstSplit.setAmount(Math.round((firstSplit.getAmount() + difference)*100.0)/100.0);
    }
}
