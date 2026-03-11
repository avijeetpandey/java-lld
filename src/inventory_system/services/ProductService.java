package services;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import exceptions.OrderNotFoundException;
import exceptions.ProductAlreadyInInventory;
import exceptions.ProductNotFound;
import exceptions.ProductOutOfStock;
import models.Order;
import models.Product;
import repository.OrderRepository;
import repository.ProductRepository;
import strategy.PaymentStrategy;

// Product or inventory service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public void createProduct(String productId, String name, Integer count) {
        Product product = productRepository.get(productId);

        if (product != null) {
            throw new ProductAlreadyInInventory("Product already in inventory");
        }

        Product createProduct = new Product(productId, name, count);
        productRepository.save(createProduct);

        System.out.println("Product with " + productId + " added into inventory");
    }

    public Integer getInventory(String productId) {
        Product product = productRepository.get(productId);
        if (product == null) {
            throw new ProductNotFound("Product with Id " + productId + " not found");
        }

        return product.getAvailableQuantity();
    }

    public synchronized void blockInventory(String productId, Integer count, String orderId) {
        Product product = productRepository.get(productId);
        ReentrantLock productLock = product.getLock();
        try {
            long timeDifference = System.currentTimeMillis() - product.getLockedAt();
            long FIVE_MINS_DIFFERENCE = 1000;
            if (product.getLockedAt() == -1) {
                // lock the product for specific time
                Integer availableQuntity = product.getAvailableQuantity();
                if(availableQuntity <= count) {
                    throw new ProductOutOfStock("Product is out of stock");
                }

                product.setAvailableQuantity(product.getAvailableQuantity() -  count);
                product.setLockedAt();
                product.getLock().lock();
                System.out.println("Inventory blocked for product with "+ productId);
            } else {
                // check the time difference
                if(timeDifference > FIVE_MINS_DIFFERENCE ) {
                    productLock.unlock();
                    product.setAvailableQuantity(product.getAvailableQuantity() + count);
                    System.out.println("Inventory unblocked for product with "+ productId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createOrder(String orderId, PaymentStrategy strategy, List<String> products) {
        Order order = new Order(orderId, strategy);
        order.setProductsInOrder(products);
        orderRepository.save(order);
        System.out.println("Order with " + orderId + " created");
    }

    public void confirmOrder(String orderId) {
        // block the inventory
        Order order = orderRepository.get(orderId);
        if(order == null) {
            throw new OrderNotFoundException("Order with id is not found");
        }
        System.out.println("Order placed successfully");
        // cleaning the lock
    }
}
