package strategy;

public class CashPayment implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Paid using Cash: " + amount);
    }
}