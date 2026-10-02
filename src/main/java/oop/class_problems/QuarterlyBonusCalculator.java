package oop.class_problems;

interface Auditable {
    String auditRecord();
}

abstract class StaffMember {
    private double baseSalary;
    private double bonusRate;

    public StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    public StaffMember(double baseSalary, double bonusRate) {
        setSalary(baseSalary);
        this.bonusRate = bonusRate;
    }

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double baseSalary) {
        if (baseSalary < 0) {
            System.out.println("rejected, salary unchanged");
            return;
        }
        this.baseSalary = baseSalary;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public abstract double calculateBonus();
}

class TeamLead extends StaffMember implements Auditable {
    private int teamSize;

    public TeamLead(double baseSalary, int teamSize) {
        super(baseSalary);
        this.teamSize = teamSize;
    }

    public TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * getBonusRate();
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize + " team members, salary $" + getSalary();
    }
}

public class QuarterlyBonusCalculator {
    public static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            Auditable auditable = (Auditable) s;
            return auditable.auditRecord();
        }
        return "No audit required";
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Constructor Chaining & Default Rate ---");
        TeamLead t1 = new TeamLead(60000, 5);
        System.out.println("t1 Bonus: " + t1.calculateBonus());

        System.out.println("\n--- Test 2: Explicit Bonus Rate ---");
        TeamLead t2 = new TeamLead(60000, 0.20, 5);
        System.out.println("t2 Bonus: " + t2.calculateBonus());

        System.out.println("\n--- Test 3: Validation in Setter ---");
        t1.setSalary(-5000);

        System.out.println("\n--- Test 4: Upcasting & Interface instanceof Check ---");
        StaffMember ref = t1; // Upcasting
        System.out.println(getAuditIfApplicable(ref));
    }
}