package coupon_engine.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import coupon_engine.models.Cart;
import coupon_engine.models.Coupon;
import coupon_engine.models.User;

public class Datastore {
    public final Map<String, User> users = new ConcurrentHashMap<>();
    public final Map<String, Cart> carts = new ConcurrentHashMap<>();
    public final Map<String, Coupon> coupons = new ConcurrentHashMap<>();
}
