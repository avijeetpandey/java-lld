package coupon_engine.strategy;

public class PercentDiscountStrategy implements DiscountStrategy {
    private final double maxCapping;
    private final double percentage;

    public PercentDiscountStrategy(double maxCapping, double percentage) {
        this.maxCapping = maxCapping;
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double totalCartValue) {
        double percentageDiscount = totalCartValue * (percentage / 100.0);
        double discount = Math.min(percentageDiscount, maxCapping);
        return Math.max(0, totalCartValue - discount);
    }
}
