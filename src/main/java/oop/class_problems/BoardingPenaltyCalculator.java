package oop.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Invalid input");
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

        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100;

        return Math.max(penalty, minimumPenalty);
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(
                calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
                calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
                calculator.calculatePenalty(1000, 16)
        );
    }
}