package flash_sale_order_system.strategy;

public class FlatPriceStrategy implements PricingStrategy {
    private final double discountAmount;

    public FlatPriceStrategy(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return Math.max(0, basePrice - discountAmount);
    }
}
