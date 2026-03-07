package rules;

public class FlatDiscountRule implements DiscountRule {

    private final double flatDiscount;
    private final double minPurchaseAmount;

    public FlatDiscountRule(double flatDiscount, double minPurchaseAmount) {
        this.flatDiscount = flatDiscount;
        this.minPurchaseAmount = minPurchaseAmount;
    }

    @Override
    public double applyDiscount(double totalCartValue) {
        if(totalCartValue >= minPurchaseAmount) {
            return totalCartValue - flatDiscount;
        }
        return totalCartValue;
    }
}
