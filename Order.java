package models;

import java.util.ArrayList;
import java.util.List;
import observer.Observer;
import state.OrderState;

public class Order {
    private List<Observer> observers = new ArrayList<>();
    private OrderState state;

    public void setState(OrderState state) {
        this.state = state;
        notifyObservers("Order state changed to: " + state.getName());
    }

    public void nextState() {
        if (state != null) {
            state.next(this);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}