package oop.assigment_problems;

public class NightlyFleetReconciliation {

    static class BusTicketAccount {

        private String bookingId;
        private double ticketFare;

        static double penaltyRate;

        static {
            penaltyRate = 1.0;
        }

        public BusTicketAccount(String bookingId, double ticketFare) {

            if (ticketFare < 0) {
                throw new IllegalArgumentException("Invalid fare");
            }

            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public final double calculatePenalty(int minutesLate) {

            if (minutesLate < 0) {
                throw new IllegalArgumentException("Invalid minutes");
            }

            if (minutesLate == 0) {
                return 0.0;
            }

            double penalty = 0;

            int first = Math.min(minutesLate, 5);
            penalty += first * ticketFare * 0.005;

            if (minutesLate > 5) {
                int second = Math.min(minutesLate, 15) - 5;
                penalty += second * ticketFare * 0.01;
            }

            if (minutesLate > 15) {
                int third = minutesLate - 15;
                penalty += third * ticketFare * 0.02;
            }

            double minimum =
                    ticketFare * penaltyRate / 100;

            return Math.max(penalty, minimum);
        }
    }


    static class SleeperCoachAccount extends BusTicketAccount {

        SleeperCoachAccount(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }

        double sleeperPenalty(int minutesLate) {
            return calculatePenalty(minutesLate) * 0.5;
        }
    }


    static void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account instanceof SleeperCoachAccount) {

            SleeperCoachAccount sleeper =
                    (SleeperCoachAccount) account;

            System.out.println(
                    "Sleeper penalty: Rs "
                    + sleeper.sleeperPenalty(minutesLate)
            );

        } else {

            System.out.println(
                    "Regular penalty: Rs "
                    + account.calculatePenalty(minutesLate)
            );
        }
    }


    static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double grandTotal = 0;

        int length = Math.min(
                accounts.length,
                Math.min(amounts.length, minutesLateArray.length)
        );

        for (int i = 0; i < length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (accounts[i] instanceof SleeperCoachAccount) {

                sleeper++;

                SleeperCoachAccount s =
                        (SleeperCoachAccount) accounts[i];

                grandTotal +=
                        s.sleeperPenalty(minutesLateArray[i]);

            } else {

                regular++;

                grandTotal +=
                        accounts[i].calculatePenalty(
                                minutesLateArray[i]);
            }
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + sleeper + " sleeper | "+ regular + " regular | "+ "grand total penalties = Rs "+ grandTotal);
    }


    public static void main(String[] args) {

        SleeperCoachAccount a1 =
                new SleeperCoachAccount("BK001", 2000);

        BusTicketAccount a2 =
                new BusTicketAccount("BK002", 1200);

        BusTicketAccount[] accounts = {a1,null,a2};

        double[] amounts = {1200,900,700};

        int[] minutesLateArray = { 10, 5, 0 };

        processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}