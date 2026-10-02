package oop.assigment_problems;

class RaceEntry {
    private static int bibCounter = 0;
    private final String entryCode;
    private String bibNumber;
    private double entryFee;
    private double totalPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        bibCounter++;
        this.entryCode = "ENTRY-" + bibCounter;
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.totalPaid = 0.0;
    }

    public String getEntryCode() {
        return entryCode;
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

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return entryFee - totalPaid;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'M') {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        if (entries != null) {
            for (RaceEntry entry : entries) {
                if (entry == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (entry instanceof RelayTeamEntry) {
                    relayCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + relayCount + " relay | " + individualCount + " individual";
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

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    public double getSponsorBonus() {
        return sponsorBonus;
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }
}

public class NightlySettlementEngine {
    public static void main(String[] args) {
        System.out.println("--- Test 1: Discount Code Validation ---");
        System.out.println("M123A valid: " + RaceEntry.isValidDiscountCode("M123A"));
        System.out.println("M12A valid: " + RaceEntry.isValidDiscountCode("M12A"));
        System.out.println("X123A valid: " + RaceEntry.isValidDiscountCode("X123A"));

        System.out.println("\n--- Test 2: Overloaded Payment & Settlement ---");
        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        eliteEntry.pay(10, "UPI");

        RaceEntry[] nightBatch = { eliteEntry, null, relayEntry };
        System.out.println(RaceEntry.settleNight(nightBatch));

        System.out.println("\n--- Test 3: Bib Counter Total ---");
        System.out.println("Total Bib Counter: " + RaceEntry.getBibCounter());
    }
}