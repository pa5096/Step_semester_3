package oop.assigment_problems;

import java.util.Arrays;

class RaceEntry {
    private String bibNumber;
    private double entryFee;
    private double totalPaid;
    private double[] lateFeeHistory;
    private int historyCount;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.totalPaid = 0.0;
        this.lateFeeHistory = new double[10];
        this.historyCount = 0;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void pay(double amount) {
        this.totalPaid += amount;
    }

    public double getBalanceDue() {
        return entryFee - totalPaid;
    }

    protected void applyLateFee(double amount) {
        this.entryFee += amount;
        if (historyCount < lateFeeHistory.length) {
            lateFeeHistory[historyCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, historyCount);
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalAudit {
    public static void main(String[] args) {
        System.out.println("--- Test 1: Doubled Late Fee Balance ---");
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println("Balance Due: " + r.getBalanceDue());

        System.out.println("\n--- Test 2: Audit Trail & Defensive Copy ---");
        double[] history = r.getLateFeeHistory();
        System.out.println("Recorded History: " + Arrays.toString(history));

        history[0] = 999;
        System.out.println("History After Tampering Attempt: " + Arrays.toString(r.getLateFeeHistory()));
    }
}