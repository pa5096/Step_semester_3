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

    public String announce() {
        return "Base Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry instanceof RunnerEntry) {
            return "Direct descendant (2 generations deep)";
        }
        return "Base generation";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0.0;
        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }
        return total;
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
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getBalanceDue();
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

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + getBibNumber() + " | Category: " + getCategory() + " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
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

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
    }
}

public class RaceFamilyHierarchy {
    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry elite = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println("--- Test 1: Announce Outputs ---");
        System.out.println(runner.announce());
        System.out.println(elite.announce());
        System.out.println(relay.announce());

        System.out.println("\n--- Test 2: Generation Classification ---");
        System.out.println(RaceEntry.classifyGeneration(elite));
        System.out.println(RaceEntry.classifyGeneration(relay));

        System.out.println("\n--- Test 3: Total Balance Calculation ---");
        RaceEntry[] entries = { runner, elite, relay };
        System.out.println("Total Balance Due: " + RaceEntry.getTotalBalanceDue(entries));
    }
}