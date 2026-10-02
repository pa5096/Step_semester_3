package oop.class_problems;

class EventTicket {
    private static int ticketsIssuedCounter = 0;
    private final String ticketId;
    private double basePrice;
    private double totalPaid;

    public EventTicket(double basePrice) {
        ticketsIssuedCounter++;
        this.ticketId = "TCK-" + (1000 + ticketsIssuedCounter);
        this.basePrice = basePrice;
        this.totalPaid = 0.0;
    }

    public String getTicketId() {
        return ticketId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void pay(double amount) {
        this.totalPaid += amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return basePrice - totalPaid;
    }

    public static int getTicketsIssued() {
        return ticketsIssuedCounter;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'F') {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (tickets != null) {
            for (EventTicket ticket : tickets) {
                if (ticket == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (ticket instanceof GroupTicket) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }
}

class GroupTicket extends EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class FestSettlementEngine {
    public static void main(String[] args) {
        System.out.println("--- Test 1: Ticket Issuance & Counter ---");
        EventTicket t1 = new EventTicket(500);
        System.out.println("t1.ticketId: " + t1.getTicketId());
        System.out.println("Tickets Issued: " + EventTicket.getTicketsIssued());

        System.out.println("\n--- Test 2: Promo Code Validation ---");
        System.out.println("F123A valid: " + EventTicket.isValidPromoCode("F123A"));
        System.out.println("F12A valid: " + EventTicket.isValidPromoCode("F12A"));
        System.out.println("X123A valid: " + EventTicket.isValidPromoCode("X123A"));

        System.out.println("\n--- Test 3: Overloaded Payments ---");
        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println("Balance due: " + t1.getBalanceDue());

        System.out.println("\n--- Test 4: Nightly Settlement Engine ---");
        EventTicket[] nightBatch = {
            new GroupTicket(2000, 5),
            null,
            new EventTicket(500)
        };
        System.out.println(EventTicket.processNightlySettlement(nightBatch));
    }
}