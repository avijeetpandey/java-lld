package coupon_engine.models;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import coupon_engine.rules.CouponCondition;
import coupon_engine.strategy.DiscountStrategy;

public class Coupon {
    private final String code;
    private int remainingUsage;
    private final DiscountStrategy strategy;
    private final List<CouponCondition> conditions;

    // concurrency controls
    private final ReentrantLock lock = new ReentrantLock();
    private final Set<String> usedByUsers = ConcurrentHashMap.newKeySet();

    public Coupon(String code, int maxUsage, DiscountStrategy discountStrategy, List<CouponCondition> conditions) {
        this.code = code;
        this.remainingUsage = maxUsage;
        this.strategy = discountStrategy;
        this.conditions = conditions;
    }

    public String getCode() {
        return code;
    }

    public DiscountStrategy getStrategy() {
        return strategy;
    }

    public List<CouponCondition> getConditions() {
        return conditions;
    }

    public void claim(String userId) {
        lock.lock();
        try {
            if(usedByUsers.contains(userId)) {
                throw new RuntimeException("coupon already used");
            }
            if(remainingUsage <= 0) {
                throw new RuntimeException("Coupon usage limit exhausted");
            }

            remainingUsage--;
            usedByUsers.add(userId);
        } finally {
            lock.unlock();
        }
    }
}
