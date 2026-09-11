package oop.class_problems;

public class FeeAccountSystem {

    static class FeeAccount {

        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            amountPaid = 0;
        }

        void pay(double amount) {

            if (amount <= 0) {
                System.out.println("Payment rejected");
            } else {
                amountPaid = amountPaid + amount;
            }
        }

        double getDue() {
            return totalFee - amountPaid;
        }
    }

    static class HostelFeeAccount extends FeeAccount {

        HostelFeeAccount(String regNo, double totalFee) {
            super(regNo, totalFee);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class ScholarshipFeeAccount extends FeeAccount {

        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee,
                              double scholarshipPercent) {
            super(regNo, totalFee);
            this.scholarshipPercent = scholarshipPercent;
        }

        double effectiveDue() {
            return getDue() - (getDue() * scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {

        FeeAccount plain =
                new FeeAccount("101", 150000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("102", 200000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("103", 180000, 20);

        plain.pay(150000);
        hostel.pay(60000);

        if (plain instanceof FeeAccount) {
            System.out.println("Plain account due: Rs "
                    + plain.getDue());
        }

        if (hostel instanceof HostelFeeAccount) {
            hostel.payInTwoInstallments(0);

            System.out.println("Hostel account due: Rs "
                    + hostel.getDue());
        }

        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println("Scholarship account effective due: Rs "
                    + scholarship.effectiveDue());
        }
    }
}