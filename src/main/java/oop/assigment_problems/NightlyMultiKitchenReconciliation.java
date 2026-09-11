package oop.assigment_problems;

public class NightlyMultiKitchenReconciliation {

    static class DeliveryAccount {

        String studentId;
        double orderValue;

        static double minimumSurgePercent;

        static {
            minimumSurgePercent = 1.0;
        }

        public DeliveryAccount(String studentId, double orderValue) {
            if (orderValue < 0) {
                throw new IllegalArgumentException("Invalid order value");
            }

            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0.0);
        }

        public final double calculateSurgeFee(int delayMinutes) {

            if (delayMinutes < 0) {
                throw new IllegalArgumentException("Invalid delay");
            }

            if (delayMinutes == 0) {
                return 0.0;
            }

            double fee = 0;

            int first = Math.min(delayMinutes, 5);
            fee += first * orderValue * 0.005;

            if (delayMinutes > 5) {
                int second = Math.min(delayMinutes, 15) - 5;
                fee += second * orderValue * 0.01;
            }

            if (delayMinutes > 15) {
                int third = delayMinutes - 15;
                fee += third * orderValue * 0.02;
            }

            double minimum =
                    orderValue * minimumSurgePercent / 100;

            return Math.max(fee, minimum);
        }
    }

    static class PremiumDeliveryAccount extends DeliveryAccount {

        public PremiumDeliveryAccount(
                String studentId,
                double orderValue) {

            super(studentId, orderValue);
        }

        public double premiumSurgeFee(int delayMinutes) {
            return calculateSurgeFee(delayMinutes) * 0.5;
        }
    }

    static void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account instanceof PremiumDeliveryAccount) {

            PremiumDeliveryAccount premium =
                    (PremiumDeliveryAccount) account;

            System.out.println(
                    "Premium surge fee: Rs "
                    + premium.premiumSurgeFee(delayMinutes)
            );

        } else {

            System.out.println(
                    "Regular surge fee: Rs "
                    + account.calculateSurgeFee(delayMinutes)
            );
        }
    }

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0;

        int length = Math.min(
                accounts.length,
                Math.min(amounts.length, delayMinutesArray.length)
        );

        for (int i = 0; i < length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (accounts[i] instanceof PremiumDeliveryAccount) {

                premium++;

                PremiumDeliveryAccount p =
                        (PremiumDeliveryAccount) accounts[i];

                grandTotal +=
                        p.premiumSurgeFee(delayMinutesArray[i]);

            } else {

                regular++;

                grandTotal +=
                        accounts[i].calculateSurgeFee(
                                delayMinutesArray[i]);
            }
        }

        System.out.println(processed + " processed | "+ nullSkipped + " null skipped | "+ premium + " premium | "+ regular + " regular | "+ "grand total surge fees = Rs "+ grandTotal);
    }

    public static void main(String[] args) {

        PremiumDeliveryAccount account1 =
                new PremiumDeliveryAccount("STU001", 500);

        DeliveryAccount account2 =
                new DeliveryAccount("STU002", 300);

        DeliveryAccount[] accounts = {account1,null,account2};
        double[] amounts = {500,400,300};
        int[] delayMinutesArray = {10,5,0};

        processBatch(accounts,amounts,delayMinutesArray);
    }
}