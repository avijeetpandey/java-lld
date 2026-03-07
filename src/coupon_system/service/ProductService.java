package service;

import exceptions.ProductAlreadyAddedException;
import models.Product;
import repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepository) {
        this.productRepo = productRepository;
    }

    public void addProduct(String productId, String name, double price, int quantity) {
        Product alreadyExists = productRepo.get(productId);
        if(alreadyExists != null) {
            Product product = new Product(productId, name, price, quantity);
            productRepo.save(product);
            System.out.println("Procuct " + productId + "added successfully");
        } else {
            throw new ProductAlreadyAddedException("Product already added");
        }
    }
}
