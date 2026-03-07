package service;

import java.time.LocalDateTime;

import enums.DiscountType;
import models.Coupon;
import repository.CouponRepository;

public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void createCoupon(String couponCode, DiscountType type, double discountValue, double minPurchaseValue, LocalDateTime expDateTime) {
        Coupon coupon = new Coupon(couponCode, couponCode, type, expDateTime);
        couponRepository.save(coupon);
        System.out.println("Coupon " + couponCode + " created successfully");
    }
}
