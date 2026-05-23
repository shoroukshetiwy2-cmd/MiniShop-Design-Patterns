package state;

import models.Order;

public class PendingState implements OrderState {
    public void next(Order order) {
        System.out.println("Changing order from Pending to Paid");
        order.setState(new PaidState());
    }

    public String getName() {
        return "Pending";
    }
}