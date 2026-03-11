package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import models.Product;

public class ProductRepository {
    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Product get(String productId) {
        return products.get(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
