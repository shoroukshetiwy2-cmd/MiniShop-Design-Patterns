package state;

import models.Order;

public interface OrderState {
    void next(Order order);
    String getName();
}