package service;

import java.util.List;

import enums.DiscountType;
import exceptions.OutOfStockException;
import models.Cart;
import models.Coupon;
import models.Product;
import models.User;
import repository.CartRepository;
import repository.CouponRepository;
import repository.ProductRepository;
import rules.FlatDiscountRule;

public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository,
            CouponRepository couponRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
    }

    public void addToCart(String cartId, String productId, int quantity) {
        Product product = productRepository.get(productId);
        Cart cart = cartRepository.get(cartId);

        if(cart == null) {
            cart = new Cart("cart", productId, cartId, null, productId, quantity, new FlatDiscountRule(400, 1000));
        }

        if(product.getQuantity() >= quantity) {
            cart.addProduct(product);
            cart.updateCartAmount();
            System.out.println("Product added to the cart successfuly");
        } else {
            throw new OutOfStockException("Requested quantity exceeds available stock");
        }
    }

    public void appleCoupon(String cardId, String couponCode) {
        Cart cart = cartRepository.get(cardId);
        Coupon coupon = couponRepository.get(couponCode);
        List<Product> products = cart.getProducts();
        String userId = cart.getUserId();
        cart.applyDiscount();
    }
}
