package chain;

public class UsernameHandler extends Handler {
    public boolean handle(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("Username is invalid");
            return false;
        }

        System.out.println("Username is valid");

        if (next != null) {
            return next.handle(username, password);
        }

        return true;
    }
}