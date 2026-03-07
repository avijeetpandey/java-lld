package repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import models.Product;

public class ProductRepository {
    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Product get(String productId) {
        if (products.containsKey(productId)) {
            return products.get(productId);
        }

        return null;
        
    }
}
