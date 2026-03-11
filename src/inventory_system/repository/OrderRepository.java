package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import models.Order;

public class OrderRepository {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public Order get(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }
}
