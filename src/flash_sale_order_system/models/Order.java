package flash_sale_order_system.models;

import java.util.UUID;

import flash_sale_order_system.enums.OrderStatus;

public class Order {
    private final String id;
    private final String productId;
    private final String userId;
    private final double amountPaid;
    private final OrderStatus status;

    public Order(String productId, String userId, double amountPaid, OrderStatus status) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.status = status;
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }
}
