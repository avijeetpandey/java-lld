package design_patterns.FactoryPattern;

public class FactoryDesignPatternDemo {
    static enum PaymentMethodType {
        UPI,
        CREDIT_CARD,
        DEBIT_CARD
    }

    interface PaymentMethod {
        void pay();
    }

    static class PaymentFactory {
        public static PaymentMethod createPayment(PaymentMethodType type) {
            switch (type) {
                case UPI:
                    return new UPIPaymentMethod();
                case DEBIT_CARD:
                    return new DebitCardPaymentMethod();
                case CREDIT_CARD:
                    return new CreditCardPaymentMethod();
                default:
                    throw new RuntimeException("Unknown payment method type");
            }
        }
    }

    static class UPIPaymentMethod implements PaymentMethod {
        @Override
        public void pay() {
            System.out.println("Paid via UPI");
        }
    }

    static class DebitCardPaymentMethod implements PaymentMethod {
        @Override
        public void pay() {
            System.out.println("Paid via debit card");
        }
    }

    static class CreditCardPaymentMethod implements PaymentMethod {
        @Override
        public void pay() {
            System.out.println("Paid by credit card");
        }
    }

    public static void main(String[] args) {
        PaymentMethod creditCard = PaymentFactory.createPayment(PaymentMethodType.CREDIT_CARD);
        creditCard.pay();

        PaymentMethod debitCard = PaymentFactory.createPayment(PaymentMethodType.DEBIT_CARD);
        debitCard.pay();
    }
}
