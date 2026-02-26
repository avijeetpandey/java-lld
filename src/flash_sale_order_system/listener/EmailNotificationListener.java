package flash_sale_order_system.listener;

import flash_sale_order_system.models.Order;
import flash_sale_order_system.observer.OrderObserver;

public class EmailNotificationListener implements OrderObserver {
    @Override
    public void onOrderSuccess(Order order) {
        System.out.println("Notification sent to user for order Id: " + order.getId());
    }
}
