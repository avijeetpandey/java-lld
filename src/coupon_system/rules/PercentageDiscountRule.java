package rules;

public class PercentageDiscountRule implements DiscountRule {
    private final double percentageDiscount;
    private final double maxDiscountValue;

    public PercentageDiscountRule(double percentageDiscount, double maxDiscountValue) {
        this.percentageDiscount = percentageDiscount;
        this.maxDiscountValue = maxDiscountValue;
    }


    @Override
    public double applyDiscount(double totalCartValue) {
        double percentageDiscount = totalCartValue - totalCartValue * (this.percentageDiscount / 100);
        if(percentageDiscount <= maxDiscountValue) {
            return percentageDiscount;
        }
        return totalCartValue;
    }
}
