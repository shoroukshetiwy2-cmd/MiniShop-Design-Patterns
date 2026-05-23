# SE369 Mini Shop - Design Patterns Project

This is a simple Java console-based Mini Shop application created for a Software Component Design course.  
The project demonstrates multiple software design patterns through a small e-commerce shopping flow.

## Project Description

The user can:

- Log in using username and password validation
- View product categories
- Add products to a shopping cart
- Apply a discount to a product
- Choose a payment method
- Create and process an order
- Receive order notifications
- Track program actions using a logger

## Design Patterns Used

| Pattern | Used In | Purpose |
|---|---|---|
| Factory | `PaymentFactory` | Creates the correct payment method object |
| Singleton | `Logger` | Ensures there is only one logger instance |
| Bridge | `Notification`, `MessageSender`, `EmailSender`, `SMSSender` | Separates notification logic from the sending method |
| Decorator | `DiscountDecorator` | Adds discount behavior to a product without changing the original product class |
| Composite | `CategoryComposite`, `ProductLeaf` | Represents product categories and products in a tree structure |
| Command | `Command`, `AddToCartCommand` | Encapsulates adding products to the cart as a command |
| Strategy | `PaymentStrategy`, `CashPayment`, `CreditCardPayment` | Allows different payment methods to be selected at runtime |
| State | `OrderState`, `PendingState`, `PaidState`, `ShippedState` | Changes order behavior/status depending on its current state |
| Observer | `Observer`, `EmailNotification`, `SMSNotification` | Notifies observers when the order state changes |
| Chain of Responsibility | `UsernameHandler`, `PasswordHandler` | Validates login input step by step |

## Project Structure

```text
src/
├── bridge/
├── chain/
├── command/
├── composite/
├── decorator/
├── factory/
├── main/
├── models/
├── observer/
├── singleton/
├── state/
└── strategy/
```

## How to Run

### Option 1: Run using IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Open the project folder.
3. Make sure the Java SDK is set to Java 17 or later.
4. Mark the `src` folder as Sources Root if needed.
5. Open:

```text
src/main/Main.java
```

6. Right-click inside `Main.java`.
7. Choose **Run 'Main.main()'**.

### Option 2: Run using Terminal

From the project folder, run:

```bash
javac -d out $(find src -name "*.java")
java -cp out main.Main
```

On Windows PowerShell, you can run:

```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d out @sources.txt
java -cp out main.Main
```

## Notes

- This project is for educational purposes.
- The application runs in the console.
- UML diagrams and the project report are included in the `docs` folder.
