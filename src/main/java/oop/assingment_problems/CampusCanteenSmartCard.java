package oop.assingment_problems;

import java.util.ArrayList;
import java.util.List;

interface PricingPlan {
    double applyDiscount(double originalPrice);
}

class DayScholarPlan implements PricingPlan {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice;
    }
}

class HostellerPlan implements PricingPlan {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.80;
    }
}

class Transaction {
    private String description;
    private double amount;
    private boolean isRefunded;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.isRefunded = false;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }
}

class SmartCard {
    private String cardId;
    private PricingPlan plan;
    private boolean isBlocked;
    private List<Transaction> transactions = new ArrayList<>();

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
        this.isBlocked = false;
    }

    public double getBalance() {
        double balance = 0.0;
        for (Transaction t : transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    public void topUp(double amount) {
        if (isBlocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }
        if (amount < 100) {
            System.out.println("Top-up failed: Minimum top-up amount is ₹100.");
            return;
        }
        if (getBalance() + amount > 5000) {
            System.out.println("Top-up failed: Maximum balance limit of ₹5000 exceeded.");
            return;
        }

        transactions.add(new Transaction("Top Up", amount));
        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n", cardId, amount, getBalance());
    }

    public void purchase(String itemName, double originalPrice) {
        if (isBlocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return;
        }

        double finalPrice = plan.applyDiscount(originalPrice);
        if (getBalance() < finalPrice) {
            System.out.printf("Purchase failed: Insufficient balance (required %.2f, available %.2f).%n", finalPrice, getBalance());
            return;
        }

        transactions.add(new Transaction(itemName, -finalPrice));
        System.out.printf("%s purchased for %.2f. Balance: ₹%.2f.%n", itemName, finalPrice, getBalance());
    }

    public void refund(String itemName) {
        if (isBlocked) {
            System.out.println("Refund failed: Card is blocked.");
            return;
        }

        for (Transaction t : transactions) {
            if (t.getDescription().equals(itemName) && t.getAmount() < 0) {
                if (t.isRefunded()) {
                    System.out.println("Refund rejected: " + itemName + " has already been refunded.");
                    return;
                }
                t.setRefunded(true);
                double refundAmount = Math.abs(t.getAmount());
                transactions.add(new Transaction("Refund: " + itemName, refundAmount));
                System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n", refundAmount, itemName, getBalance());
                return;
            }
        }
        System.out.println("Refund failed: No purchase record found for " + itemName + ".");
    }

    public void printMiniStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mini-statement for ").append(cardId).append(": ");
        for (int i = 0; i < transactions.size(); i++) {
            double amt = transactions.get(i).getAmount();
            if (amt > 0) {
                sb.append("+").append(String.format("%.2f", amt));
            } else {
                sb.append(String.format("%.2f", amt));
            }
            if (i < transactions.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" = ₹").append(String.format("%.2f", getBalance())).append(".");
        System.out.println(sb.toString());
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500);
        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);

        card.purchase("Bulk Items", 400);

        card.refund("Veg Thali");
        card.refund("Veg Thali");

        card.printMiniStatement();
    }
}