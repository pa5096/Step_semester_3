package oop.class_problems;

import java.util.Arrays;

class EventTicket {
    private double basePrice;
    private double totalPaid;
    private double[] lateFeeHistory;
    private int historyCount;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.totalPaid = 0.0;
        this.lateFeeHistory = new double[10];
        this.historyCount = 0;
    }

    public void pay(double amount) {
        this.totalPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - totalPaid;
    }

    protected void applyLateFee(double amount) {
        this.basePrice += amount;
        if (historyCount < lateFeeHistory.length) {
            lateFeeHistory[historyCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, historyCount);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LatePenaltyAudit {
    public static void main(String[] args) {
        System.out.println("--- Test 1: Doubled Penalty & Balance Check ---");
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println("Balance due: " + w.getBalanceDue());

        System.out.println("\n--- Test 2: Audit History & Defensive Copy Check ---");
        double[] history = w.getLateFeeHistory();
        System.out.println("History before tamper: " + Arrays.toString(history));

        history[0] = 999; // Attempt to tamper with returned array
        System.out.println("History after tamper attempt: " + Arrays.toString(w.getLateFeeHistory()));
    }
}