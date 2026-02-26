package coupon_engine.services;

import coupon_engine.models.Cart;
import coupon_engine.models.Coupon;
import coupon_engine.models.User;
import coupon_engine.repository.Datastore;
import coupon_engine.rules.CouponCondition;

public class CouponService {
    private final Datastore db;

    public CouponService(Datastore db) {
        this.db = db;
    }

    public double checkout(String userId, String couponCode) {
        Cart cart = db.carts.get(userId);
        User user = db.users.get(userId);

        if (cart == null) {
            throw new RuntimeException("Cart is empty");
        }

        if (couponCode == null || couponCode.isEmpty()) {
            throw new RuntimeException("Coupon code is empty");
        }

        Coupon coupon = db.coupons.get(couponCode);

        for (CouponCondition condition : coupon.getConditions()) {
            if (!condition.isSatisfied(cart, user)) {
                throw new RuntimeException("Condition not met: " + condition.getErrorMessage());
            }
        }

        coupon.claim(userId);

        return coupon.getStrategy().applyDiscount(cart.getTotalValue());
    }
}
