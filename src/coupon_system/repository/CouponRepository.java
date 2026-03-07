package repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import models.Coupon;

public class CouponRepository {
    private final Map<String , Coupon> coupons = new ConcurrentHashMap<>();

    public void save(Coupon coupon) {
        coupons.put(coupon.getId(), coupon);
    }

    public Coupon get(String couponId) {
        if(coupons.containsKey(couponId)) {
            coupons.get(couponId);
        }
        return null;
    }
}
