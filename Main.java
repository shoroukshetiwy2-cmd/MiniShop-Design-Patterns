package main;

import bridge.EmailSender;
import bridge.Notification;
import bridge.OrderNotification;
import bridge.SMSSender;
import chain.PasswordHandler;
import chain.UsernameHandler;
import command.AddToCartCommand;
import command.Command;
import composite.CategoryComposite;
import composite.ProductLeaf;
import decorator.DiscountDecorator;
import factory.PaymentFactory;
import models.Cart;
import models.Order;
import models.Product;
import observer.EmailNotification;
import observer.SMSNotification;
import singleton.Logger;
import state.PendingState;
import strategy.PaymentStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Logger logger = Logger.getInstance();
        logger.log("Mini Shop started");

        // =========================
        // 1. Login input from user
        // Chain of Responsibility
        // =========================
        System.out.println("===== Welcome to Mini Shop =====");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UsernameHandler usernameCheck = new UsernameHandler();
        PasswordHandler passwordCheck = new PasswordHandler();
        usernameCheck.setNext(passwordCheck);

        boolean loginOk = usernameCheck.handle(username, password);

        if (!loginOk) {
            System.out.println("Login failed. Program stopped.");
            logger.log("Login failed");
            scanner.close();
            return;
        }

        System.out.println("Login successful\n");

        // =========================
        // 2. Show product categories
        // Composite Pattern
        // =========================
        CategoryComposite electronics = new CategoryComposite("Electronics");
        electronics.add(new ProductLeaf("Laptop"));
        electronics.add(new ProductLeaf("Mouse"));
        electronics.add(new ProductLeaf("Keyboard"));
        electronics.add(new ProductLeaf("Headphones"));

        System.out.println("Available Category:");
        electronics.show();
        System.out.println();

        // =========================
        // 3. Product list
        // Some products are normal, one uses Decorator
        // =========================
        Product laptop = new Product("Laptop", 1000);
        Product discountedLaptop = new DiscountDecorator(laptop); // Decorator Pattern

        Product mouse = new Product("Mouse", 50);
        Product keyboard = new Product("Keyboard", 100);
        Product headphones = new Product("Headphones", 150);

        Product[] products = {
                discountedLaptop,
                mouse,
                keyboard,
                headphones
        };

        Cart cart = new Cart();

        // =========================
        // 4. User chooses products
        // Command Pattern
        // =========================
        boolean shopping = true;

        while (shopping) {
            System.out.println("===== Product List =====");

            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ". " + products[i].getName() + " - " + products[i].getPrice());
            }

            System.out.println("0. Finish shopping");
            System.out.print("Choose product number: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.\n");
                continue;
            }

            if (choice == 0) {
                shopping = false;
            } else if (choice >= 1 && choice <= products.length) {
                Product selectedProduct = products[choice - 1];

                Command addProductCommand = new AddToCartCommand(cart, selectedProduct);
                addProductCommand.execute();

                System.out.println(selectedProduct.getName() + " added to cart.\n");
            } else {
                System.out.println("Invalid choice. Try again.\n");
            }
        }

        if (cart.getTotal() == 0) {
            System.out.println("Cart is empty. Program stopped.");
            logger.log("No products selected");
            scanner.close();
            return;
        }

        System.out.println();
        cart.showCart();
        System.out.println();

        // =========================
        // 5. Payment choice
        // Factory + Strategy Pattern
        // =========================
        System.out.println("===== Payment Method =====");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.print("Choose payment method: ");

        String paymentInput = scanner.nextLine();

        String paymentType;

        if (paymentInput.equals("2")) {
            paymentType = "credit";
        } else {
            paymentType = "cash";
        }

        PaymentStrategy payment = PaymentFactory.createPayment(paymentType);
        payment.pay(cart.getTotal());

        System.out.println();

        // =========================
        // 6. Order state
        // State Pattern
        // Observer Pattern
        // =========================
        Order order = new Order();

        order.addObserver(new EmailNotification());
        order.addObserver(new SMSNotification());

        order.setState(new PendingState());
        order.nextState();
        order.nextState();

        System.out.println();

        // =========================
        // 7. Send notifications
        // Bridge Pattern
        // =========================
        Notification emailNotification = new OrderNotification(new EmailSender());
        Notification smsNotification = new OrderNotification(new SMSSender());

        emailNotification.send("Thank you, " + username + ". Your order was received.");
        smsNotification.send("Your order is now being processed.");

        logger.log("Mini Shop finished");

        scanner.close();
    }
}