package oop.assigment_problems;

class RaceEntry {
    private String bibNumber;
    private double entryFee;
    private double totalPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.totalPaid = 0.0;
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

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
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
}

public class RaceEntryValidator {
    public static void main(String[] args) {
        System.out.println("--- Test 1: Invalid Bib Number ---");
        try {
            new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Output: construction rejected");
        }

        System.out.println("\n--- Test 2: Runner Entry Balance ---");
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println("Output: " + r.getBalanceDue());

        System.out.println("\n--- Test 3: Batch Registration ---");
        String[] batch = {"BIB1", "B1", "BIB2"};
        System.out.println("Output: " + RaceEntry.registerBatch(batch, 80));
    }
}