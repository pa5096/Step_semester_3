package oop.class_problems;

import java.util.ArrayList;
import java.util.List;

interface IPaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements IPaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment via Credit Card successful.");
        return true;
    }
}

class DigitalWalletPayment implements IPaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment via Digital Wallet failed.");
        return false;
    }
}

class LineItem {
    private String itemName;
    private int quantity;
    private double unitPrice;

    public LineItem(String itemName, int quantity, double unitPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Order {
    private static int idCounter = 123;
    private int orderId;
    private List<LineItem> items = new ArrayList<>();
    private String status = "Created";

    public Order() {
        this.orderId = idCounter++;
        System.out.println("Order created.");
    }

    public void addItem(LineItem item) {
        items.add(item);
        System.out.println("Added " + item.getItemName() + " (Qty " + item.getQuantity() + ")");
    }

    public void placeAndPay(IPaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");
        double total = 0;
        for (LineItem item : items) {
            total += item.getTotalPrice();
        }

        if (paymentMethod.processPayment(total)) {
            this.status = "Paid";
            System.out.println("Order status: " + status + ".");
            System.out.println("Notification: Order #" + orderId + " placed and paid.");
        } else {
            this.status = "Pending Payment";
            System.out.println("Order status: " + status + ".");
            System.out.println("Notification: Order #" + orderId + " placed, awaiting payment.");
        }
    }
}

public class FoodOrderPaymentSystem {
    public static void main(String[] args) {
        Order order1 = new Order();
        order1.addItem(new LineItem("Pizza", 2, 10.0));
        order1.addItem(new LineItem("Soda", 1, 2.5));

        Order emptyOrder = new Order();
        emptyOrder.placeAndPay(new CreditCardPayment());

        order1.placeAndPay(new CreditCardPayment());

        Order order2 = new Order();
        order2.addItem(new LineItem("Burger", 1, 8.0));
        order2.placeAndPay(new DigitalWalletPayment());
    }
}