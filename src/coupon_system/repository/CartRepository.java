package repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import models.Cart;

public class CartRepository {
    private final Map<String , Cart> carts = new ConcurrentHashMap<>();

    public void save(Cart cart) {
        carts.put(cart.getId(), cart);
    }

    public Cart get(String cartId) {
        if(carts.containsKey(cartId)) {
            return carts.get(cartId);
        }

        return null;
    }

    public void clear() {
        carts.clear();
    }
}
