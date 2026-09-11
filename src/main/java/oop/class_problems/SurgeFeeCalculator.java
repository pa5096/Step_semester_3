package oop.class_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
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

        double minimumFee =
                orderValue * minimumSurgePercent / 100;

        return Math.max(fee, minimumFee);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(
                calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
                calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
                calculator.calculateSurgeFee(500, 16)
        );
    }
}