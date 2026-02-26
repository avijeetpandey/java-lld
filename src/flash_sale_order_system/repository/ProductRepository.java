package flash_sale_order_system.repository;

import java.util.HashMap;
import java.util.Map;

import flash_sale_order_system.models.Product;

public class ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Product get(String id) {
        return products.get(id);
    }
}
