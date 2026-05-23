package state;

import models.Order;

public class PaidState implements OrderState {
    public void next(Order order) {
        System.out.println("Changing order from Paid to Shipped");
        order.setState(new ShippedState());
    }

    public String getName() {
        return "Paid";
    }
}