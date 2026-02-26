package coupon_engine.rules;

import coupon_engine.models.Cart;
import coupon_engine.models.User;

public interface CouponCondition {
    boolean isSatisfied(Cart cart, User user);
    String getErrorMessage();
}
