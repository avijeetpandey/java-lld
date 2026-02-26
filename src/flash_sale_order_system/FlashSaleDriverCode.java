package flash_sale_order_system;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import flash_sale_order_system.enums.Role;
import flash_sale_order_system.exceptions.FlashSaleException;
import flash_sale_order_system.listener.EmailNotificationListener;
import flash_sale_order_system.models.Order;
import flash_sale_order_system.models.Product;
import flash_sale_order_system.models.User;
import flash_sale_order_system.repository.OrderRepository;
import flash_sale_order_system.repository.ProductRepository;
import flash_sale_order_system.repository.UserRepository;
import flash_sale_order_system.services.FlashSaleService;
import flash_sale_order_system.strategy.FlatPriceStrategy;

public class FlashSaleDriverCode {
    public static void main(String[] args) throws InterruptedException {
        UserRepository userRepository = new UserRepository();
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();

        FlashSaleService service = new FlashSaleService(userRepository, orderRepository, productRepository);
        service.addObservers(new EmailNotificationListener());

        String userId = service.registerUser(new User("Aman",Role.ADMIN,"aman@flash.com"));

        for (int i = 1; i <= 50; i++) {
            service.registerUser(new User("U"+i,Role.CUSTOMER,"u"+i+"@flash.com"));
        }

        Product product = new Product("iPhone16", 20000, 10, new FlatPriceStrategy(1000));

        service.addProduct(product, userId);

        System.out.println("Starting Flash Sale! 50 users fighting for 10 iPhones priced at 20000...");

        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(50);
        ExecutorService executor = Executors.newFixedThreadPool(50);

        for (int i = 1; i <= 50; i++) {
            final String usersInQueue = "U" + i;
            executor.submit(() -> {
                try {
                    startGate.await();
                    Order order = service.placeOrder(usersInQueue, product.getId());
                    System.out.println("WINNER: " + order);
                } catch (FlashSaleException e) {
                    // Expected to fail for 47 users
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endGate.countDown();
                }
            });
        }

        startGate.countDown(); 
        endGate.await(); 
        executor.shutdown();
        
        System.out.println("Flash Sale Over.");
    }
}
