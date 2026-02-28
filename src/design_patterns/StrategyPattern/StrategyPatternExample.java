package design_patterns.StrategyPattern;

public class StrategyPatternExample {
    interface PaymentStrategy {
        public boolean pay(double amount);
    }

    static class CardPaymentStrategy implements PaymentStrategy {
        @Override
        public boolean pay(double amount) {
            System.out.println("Paid by card" + amount);
            return true;
        }
    }

    static class UPIPaymentStrategy implements PaymentStrategy {
        @Override
        public boolean pay(double amount) {
            System.out.println("Paid by UPI " + amount);
            return true;
        }
    }

    static class ShoppingCart {
        private PaymentStrategy strategy;

        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }
        
        public void checkout(double amount) {
            strategy.pay(amount);
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new UPIPaymentStrategy());
        cart.checkout(1200);

        ShoppingCart cartTwo = new ShoppingCart();
        cartTwo.setPaymentStrategy(new CardPaymentStrategy());
        cartTwo.checkout(1500);
    }
}
