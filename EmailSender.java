package bridge;

public class EmailSender implements MessageSender {

    public void sendMessage(String text) {
        System.out.println("Email Sender: " + text);
    }
}