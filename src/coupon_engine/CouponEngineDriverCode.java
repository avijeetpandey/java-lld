package coupon_engine;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import coupon_engine.enums.Category;
import coupon_engine.models.Cart;
import coupon_engine.models.Coupon;
import coupon_engine.models.Item;
import coupon_engine.models.User;
import coupon_engine.repository.Datastore;
import coupon_engine.rules.CouponCondition;
import coupon_engine.rules.MinCartCondition;
import coupon_engine.rules.SpecificCategoryCondition;
import coupon_engine.services.CouponService;
import coupon_engine.strategy.PercentDiscountStrategy;

public class CouponEngineDriverCode {
    public static void main(String[] args) throws InterruptedException {
        Datastore db = new Datastore();
        CouponService service = new CouponService(db);

        List<CouponCondition> conditions = Arrays.asList(new MinCartCondition(2000),
                new SpecificCategoryCondition(Category.ELECTRONICS));

        Coupon coupon = new Coupon("DIWALI50", 5, new PercentDiscountStrategy(1000, 50), conditions);

        db.coupons.put(coupon.getCode(), coupon);
        int totalUser = 100;

        for (int i = 0; i < totalUser; i++) {
            String userId = "U" + i;
            db.users.put(userId, new User(userId, "Aman"));
            Cart cart = new Cart(userId);
            cart.addItem(new Item("ITEM1", 2500, Category.ELECTRONICS));
            db.carts.put(userId, cart);
        }

        System.out.println("Starting Mega Sale! 100 users fighting for 5 DIWALI50 coupons...");

        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(totalUser);
        ExecutorService executor = Executors.newFixedThreadPool(totalUser);

        for (int i = 1; i <= totalUser; i++) {
            final String userId = "U" + i;
            executor.submit(() -> {
                try {
                    startGate.await();
                    double finalPrice = service.checkout(userId, "DIWALI50");
                    System.out.println("✅ WINNER! " + userId + " paid: ₹" + finalPrice + " (Original: ₹2500)");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endGate.countDown();
                }
            });
        }

        startGate.countDown(); // Release the hounds
        endGate.await();
        executor.shutdown();

        System.out.println("Sale Ended. Exactly 5 winners should be printed above.");
    }
}
