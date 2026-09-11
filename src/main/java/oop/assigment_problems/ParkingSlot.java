package oop.assigment_problems;

public class ParkingSlot {

    String slotId;
    boolean occupied;

    ParkingSlot(String slotId, boolean occupied) {
        this.slotId = slotId;
        this.occupied = occupied;
    }

    void park(String carName) {

        if (!occupied) {
            occupied = true;
            System.out.println(carName + " parked in " + slotId);
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (int i = 0; i < slots.length; i++) {

            if (!slots[i].occupied) {
                return slots[i];
            }
        }

        return null;
    }

    static void safePark(ParkingSlot[] slots, String carName) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot == null) {
            System.out.println("No slot available for " + carName);
        } else {
            slot.park(carName);
        }
    }

    public static void main(String[] args) {

        ParkingSlot s1 =
                new ParkingSlot("A-01", true);

        ParkingSlot s2 =
                new ParkingSlot("A-02", false);

        ParkingSlot[] slots = {s1, s2};

        safePark(slots, "Car1");

        safePark(slots, "Car2");
    }
}