package oop.class_problems;

class EventTicket {
    private double basePrice;
    private double totalPaid;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.totalPaid = 0.0;
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

    public String printTicket() {
        return "Standard Balance: " + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + getBalanceDue();
    }
}

public class NightlyTicketAnnouncer {
    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();
        for (EventTicket ticket : tickets) {
            sb.append(ticket.printTicket());
            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop = (WorkshopTicket) ticket;
                sb.append(" [Track via downcast: ").append(workshop.getTrack()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Batch Print Output ---");
        EventTicket[] tickets = {
            new EventTicket(500),
            new WorkshopTicket(1200, "AI/ML")
        };
        System.out.println(batchPrint(tickets));

        System.out.println("\n--- Test 2: Runtime ClassCastException Check ---");
        try {
            EventTicket plain = new EventTicket(500);
            WorkshopTicket bad = (WorkshopTicket) plain;
            System.out.println(bad);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}