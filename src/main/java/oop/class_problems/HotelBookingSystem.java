package oop.class_problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long days) {
        return days * 150.0;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long days) {
        return days * 200.0;
    }
}

class Reservation {
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCancelled;

    public Reservation(Room room, LocalDate startDate, LocalDate endDate) {
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCancelled = false;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {
        if (isCancelled) return false;
        return (start.isBefore(endDate) && end.isAfter(startDate));
    }

    public long getDurationInDays() {
        return endDate.toEpochDay() - startDate.toEpochDay();
    }
}

public class HotelBookingSystem {
    private List<Reservation> reservations = new ArrayList<>();

    public void bookRoom(Room room, String startStr, String endStr) {
        LocalDate start = LocalDate.parse(startStr);
        LocalDate end = LocalDate.parse(endStr);

        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && res.overlaps(start, end)) {
                System.out.println("Booking failed: " + room.getRoomNumber() + " is not available for " + startStr + " to " + endStr + ".");
                return;
            }
        }

        Reservation newRes = new Reservation(room, start, end);
        reservations.add(newRes);
        double totalPrice = room.calculatePrice(newRes.getDurationInDays());
        System.out.printf("%s booked from %s to %s. Total price: $%.2f%n", room.getRoomNumber(), startStr, endStr, totalPrice);
    }

    public void cancelReservation(Room room) {
        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && !res.isCancelled()) {
                res.cancel();
                System.out.println("Reservation for " + room.getRoomNumber() + " cancelled successfully.");
                return;
            }
        }
        System.out.println("No active reservation found for " + room.getRoomNumber() + ".");
    }

    public static void main(String[] args) {
        HotelBookingSystem system = new HotelBookingSystem();
        Room deluxe = new DeluxeRoom("Deluxe Room 101");
        Room standard = new StandardRoom("Standard Room 205");

        system.bookRoom(deluxe, "2024-12-01", "2024-12-05");
        system.bookRoom(standard, "2024-12-03", "2024-12-07");
        system.bookRoom(deluxe, "2024-12-03", "2024-12-07");
        system.cancelReservation(deluxe);
    }
}