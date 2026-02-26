package flash_sale_order_system.services;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

import flash_sale_order_system.enums.OrderStatus;
import flash_sale_order_system.enums.Role;
import flash_sale_order_system.exceptions.FlashSaleException;
import flash_sale_order_system.exceptions.OutOfStockException;
import flash_sale_order_system.models.Order;
import flash_sale_order_system.models.Product;
import flash_sale_order_system.models.User;
import flash_sale_order_system.observer.OrderObserver;
import flash_sale_order_system.repository.OrderRepository;
import flash_sale_order_system.repository.ProductRepository;
import flash_sale_order_system.repository.UserRepository;

public class FlashSaleService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final List<OrderObserver> observers = new ArrayList<>();

    public FlashSaleService(UserRepository userRepository, OrderRepository orderRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void addObservers(OrderObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.onOrderSuccess(order);
        }
    }

    public String registerUser(User user) {
       return userRepository.save(user);
    }

    public void addProduct(Product product, String userId) {
        User user = userRepository.get(userId);

        if (user == null || user.getRole() != Role.ADMIN) {
            throw new RuntimeException("User not authorised");
        }

        productRepository.save(product);
    }

    public Order placeOrder(String userId, String productId) {

        User user = userRepository.get(userId);
        Product product = productRepository.get(productId);
        if (user == null)
            throw new FlashSaleException("User not found");
        if (product == null)
            throw new FlashSaleException("Product not found");

        // lock the product
        product.getLock().lock();
        try {
            if (product.getSuccessfullBuyers().contains(userId)) {
                throw new DuplicateFormatFlagsException("Duplicate order not allowed");
            }

            if (product.getAvailableInventory() <= 0) {
                throw new OutOfStockException("Product out of stock");
            }

            product.decrementInventory();
            product.getSuccessfullBuyers().add(userId);

            double finalPrice = product.getFinalPrice();

            Order order = new Order(productId, userId, finalPrice, OrderStatus.SUCCESS);

            orderRepository.save(order);

            notifyObservers(order);

            return order;

        } finally {
            product.getLock().unlock();
        }
    }
}
