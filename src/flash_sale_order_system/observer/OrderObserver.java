package flash_sale_order_system.observer;

import flash_sale_order_system.models.Order;

public interface OrderObserver {
    void onOrderSuccess(Order order);
}
