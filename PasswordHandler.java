package chain;

public class PasswordHandler extends Handler {
    public boolean handle(String username, String password) {
        if (password == null || password.length() < 4) {
            System.out.println("Password is invalid");
            return false;
        }

        System.out.println("Password is valid");

        if (next != null) {
            return next.handle(username, password);
        }

        return true;
    }
}