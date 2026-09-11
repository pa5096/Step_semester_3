package oop.class_problems;

public class FareSplitter {

    String tripId;
    double totalFare;
    int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {

        if (totalFare < 0 || passengerCount <= 0) {
            throw new IllegalArgumentException("Invalid fare or passenger count");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        if (totalFare == 0) {
            return result;
        }

        double fareInPaise = Math.round(totalFare * 100);
        double share = Math.floor(fareInPaise / passengerCount);
        double remainder = fareInPaise - (share * passengerCount);

        for (int i = 0; i < passengerCount; i++) {
            result[i] = share / 100.0;
        }

        result[passengerCount - 1] =
                (share + remainder) / 100.0;

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter f1 =
                new FareSplitter("TRIP001", 100000, 3);

        double[] result = f1.fareBreakdown();

        System.out.print("[");

        for (int i = 0; i < result.length; i++) {

            System.out.print(result[i]);

            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");

        FareSplitter f2 =
                new FareSplitter("TRIP003");

        double[] result2 = f2.fareBreakdown();

        System.out.print("[");

        for (int i = 0; i < result2.length; i++) {

            System.out.print(result2[i]);

            if (i < result2.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}