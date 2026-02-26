package coupon_engine.rules;

import coupon_engine.models.Cart;
import coupon_engine.models.User;

public class MinCartCondition implements CouponCondition {
    private final double minCartValue;

    public MinCartCondition(double minCartValue) {
        this.minCartValue = minCartValue;
    }

    @Override
    public boolean isSatisfied(Cart cart, User user) {
        return cart.getTotalValue() >= minCartValue;
    }

    @Override
    public String getErrorMessage() {
        return "Cart value must be atleast $" + minCartValue;
    }
}
