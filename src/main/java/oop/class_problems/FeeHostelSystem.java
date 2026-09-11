package oop.class_problems;

public class FeeHostelSystem {
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
    }

    static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds) {
            this.roomNo = roomNo;
            this.beds = beds;
            occupied = 0;
        }

        boolean allot(String name) {
            if (occupied < beds) {
                occupied++;
                return true;
            }
            return false;
        }
    }

    static class SrmStudent {
        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;
        static int totalStudents = 0;

        SrmStudent(String name, String regNo, double fee) {
            this.name = name;
            this.regNo = regNo;
            feeAccount = new HostelFeeAccount(regNo, fee);
            room = null;
            totalStudents++;
        }

        String fullStatus() {
            if (room == null) {
                return name + " | Due: Rs " + feeAccount.getDue() + " | Room: unallotted";
            }
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + room.roomNo;
        }
    }

    public static void main(String[] args) {
        SrmStudent s1 = new SrmStudent("Ravi", "101", 200000);
        SrmStudent s2 = new SrmStudent("Anitha", "102", 200000);
        SrmStudent s3 = new SrmStudent("Karthik", "103", 200000);

        HostelRoom r1 = new HostelRoom("C-214", 1);
        HostelRoom r2 = new HostelRoom("C-507", 1);

        if (r1.allot(s1.name)) {
            s1.room = r1;
        }

        if (r2.allot(s2.name)) {
            s2.room = r2;
        }

        s1.feeAccount.pay(60000);
        s2.feeAccount.pay(20000);
        s3.feeAccount.pay(-500);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}