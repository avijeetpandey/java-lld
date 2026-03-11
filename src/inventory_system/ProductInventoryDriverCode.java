import java.util.ArrayList;
import java.util.List;

import repository.OrderRepository;
import repository.ProductRepository;
import services.ProductService;
import strategy.CardPaymentStrategy;

public class ProductInventoryDriverCode {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();

        ProductService productService = new ProductService(productRepository, orderRepository);

        // creating the product
        productService.createProduct("PROD1", "Detergent", 20);
        productService.createProduct("PROD2", "Soap", 10);

        // getting available quantities of the product created
        System.out.println(productService.getInventory("PROD1"));
        System.out.println(productService.getInventory("PROD2"));

        List<String> productsInOrder = new ArrayList<>();
        productsInOrder.add("PROD1");

        productService.createOrder("ORDER1", new CardPaymentStrategy(), productsInOrder);

        // blocking the inventory
        productService.blockInventory("PROD1", 4, "ORDER1");

        // confirming the order
        productService.confirmOrder("ORDER1");
    }
}
