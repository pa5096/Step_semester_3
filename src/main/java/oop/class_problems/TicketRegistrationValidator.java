package oop.class_problems;

class EventTicket {
    private String attendeeId;
    private double basePrice;
    private double totalPaid;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Attendee ID must be non-blank and at least 4 characters long.");
        }
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.totalPaid = 0.0;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void pay(double amount) {
        this.totalPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - totalPaid;
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }
}

public class TicketRegistrationValidator {
    public static void main(String[] args) {
        System.out.println("--- Test 1: Invalid Attendee ID ---");
        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("Output: construction rejected");
        }

        System.out.println("\n--- Test 2: WorkshopTicket Payment ---");
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println("Output: " + w.getBalanceDue());

        System.out.println("\n--- Test 3: Batch Registration ---");
        String[] batch = {"STU1", "ST1", "STU2", "", "STU3"};
        System.out.println("Output: " + EventTicket.registerBatch(batch, 500));
    }
}