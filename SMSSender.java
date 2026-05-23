package bridge;

public class SMSSender implements MessageSender {

    public void sendMessage(String text) {
        System.out.println("SMS Sender: " + text);
    }
}