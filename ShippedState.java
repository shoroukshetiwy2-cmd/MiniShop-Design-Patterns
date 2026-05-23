package state;

import models.Order;

public class ShippedState implements OrderState {
    public void next(Order order) {
        System.out.println("Order is already shipped");
    }

    public String getName() {
        return "Shipped";
    }
}