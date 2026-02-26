package flash_sale_order_system.repository;

import java.util.HashMap;
import java.util.Map;

import flash_sale_order_system.models.Order;

public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }
}
