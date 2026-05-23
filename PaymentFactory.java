package factory;

import strategy.*;

public class PaymentFactory {

    public static PaymentStrategy createPayment(String type) {

        if(type.equalsIgnoreCase("cash")) {
            return new CashPayment();
        }

        return new CreditCardPayment();
    }
}