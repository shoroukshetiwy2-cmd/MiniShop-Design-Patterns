package bridge;

public class OrderNotification extends Notification {

    public OrderNotification(MessageSender sender) {
        super(sender);
    }

    public void send(String text) {
        sender.sendMessage(text);
    }
}