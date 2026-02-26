package book_my_show.factory;

import book_my_show.enums.PaymentType;
import book_my_show.strategy.payment.CardStrategy;
import book_my_show.strategy.payment.PaymentStrategy;
import book_my_show.strategy.payment.UPIStrategy;

public class PaymentStrategyFactory {
    public static PaymentStrategy getStrategy(PaymentType paymentType) {
        return switch(paymentType) {
            case UPI -> new UPIStrategy();
            case CARD -> new CardStrategy();
        };
    }
}
