package oop.class_problems;

import java.util.ArrayList;

public class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn; 

    public BusTicket(String passengerName, String destination) {

        if (passengerName == null ||
            passengerName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Invalid passenger name"
            );
        }

        for (int i = 0; i < passengerName.length(); i++) {

            char ch = passengerName.charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {

                throw new IllegalArgumentException(
                    "Invalid passenger name"
                );
            }
        }
        if (destination == null ||
            destination.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Invalid destination"
            );
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }


    public void markCheckedIn() {

        if (checkedIn) {
            System.out.println("Already checked in");
        } else {
            checkedIn = true;
            System.out.println("Checked in");
        }
    }


    static void processBatch(String[][] rawBookings) {

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        ArrayList<String> acceptedBookings =
                new ArrayList<>();

        for (int i = 0; i < rawBookings.length; i++) {

            String name = rawBookings[i][0];
            String destination = rawBookings[i][1];

            try {

                BusTicket ticket =
                        new BusTicket(name, destination);

                String key =
                        name.trim() + "|" + destination.trim();

                if (acceptedBookings.contains(key)) {

                    duplicates++;

                } else {

                    acceptedBookings.add(key);
                    valid++;
                }

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        System.out.println(
            "Valid: " + valid
            + " | Rejected: " + rejected
            + " | Duplicates skipped: " + duplicates
        );
    }


    public static void main(String[] args) {

        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {"   ", "  "}
        };

        processBatch(rawBookings);
    }
}