package coupon_engine.strategy;

public class FlatDiscountStrategy implements DiscountStrategy {
    private final double flatAmount;

    public FlatDiscountStrategy(double flatAmount) {
        this.flatAmount = flatAmount;
    }

    @Override
    public double applyDiscount(double totalCartValue) {
        return Math.max(0, totalCartValue - flatAmount);
    }
}
