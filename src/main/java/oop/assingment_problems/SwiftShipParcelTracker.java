package oop.assingment_problems;

import java.util.ArrayList;
import java.util.List;

enum ParcelStatus {
    BOOKED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}

interface ShippingType {
    double calculateCharge(double weightKg);
    String getName();
}

class StandardShipping implements ShippingType {
    @Override
    public double calculateCharge(double weightKg) {
        return 40.0 + (10.0 * weightKg);
    }

    @Override
    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingType {
    @Override
    public double calculateCharge(double weightKg) {
        return 80.0 + (15.0 * weightKg);
    }

    @Override
    public String getName() {
        return "Express";
    }
}

class FragileShipping implements ShippingType {
    private StandardShipping standard = new StandardShipping();

    @Override
    public double calculateCharge(double weightKg) {
        return standard.calculateCharge(weightKg) + 50.0;
    }

    @Override
    public String getName() {
        return "Fragile";
    }
}

interface NotificationChannel {
    void notify(String parcelId, ParcelStatus status);
}

class SmsChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

class Parcel {
    private String parcelId;
    private double weightKg;
    private ShippingType shippingType;
    private ParcelStatus currentStatus;
    private List<NotificationChannel> channels = new ArrayList<>();

    public Parcel(String parcelId, double weightKg, ShippingType shippingType) {
        this.parcelId = parcelId;
        this.weightKg = weightKg;
        this.shippingType = shippingType;
        this.currentStatus = ParcelStatus.BOOKED;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(parcelId, currentStatus);
        }
    }

    public double getCharge() {
        return shippingType.calculateCharge(weightKg);
    }

    public void updateStatus(ParcelStatus newStatus) {
        if (!isValidTransition(currentStatus, newStatus)) {
            System.out.println("Invalid transition: " + currentStatus + " -> " + newStatus + " is not allowed.");
            return;
        }
        this.currentStatus = newStatus;
        notifyChannels();
    }

    public void cancel() {
        if (currentStatus != ParcelStatus.BOOKED) {
            System.out.println("Cancellation failed: " + parcelId + " can be cancelled only while BOOKED.");
            return;
        }
        this.currentStatus = ParcelStatus.CANCELLED;
        System.out.println("Parcel " + parcelId + " has been cancelled.");
    }

    private boolean isValidTransition(ParcelStatus from, ParcelStatus to) {
        if (from == ParcelStatus.BOOKED && to == ParcelStatus.PICKED_UP) return true;
        if (from == ParcelStatus.PICKED_UP && to == ParcelStatus.IN_TRANSIT) return true;
        if (from == ParcelStatus.IN_TRANSIT && to == ParcelStatus.OUT_FOR_DELIVERY) return true;
        if (from == ParcelStatus.OUT_FOR_DELIVERY && to == ParcelStatus.DELIVERED) return true;
        return false;
    }

    public String getParcelId() {
        return parcelId;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public double getWeightKg() {
        return weightKg;
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        Parcel parcel = new Parcel("P101", 2.0, new ExpressShipping());
        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        System.out.printf("Parcel %s booked (%s, %.0f kg). Charge: ₹%.2f.%n",
                parcel.getParcelId(), parcel.getShippingType().getName(), parcel.getWeightKg(), parcel.getCharge());
        parcel.notifyChannels();

        parcel.updateStatus(ParcelStatus.PICKED_UP);
        parcel.cancel();
        parcel.updateStatus(ParcelStatus.IN_TRANSIT);
        parcel.updateStatus(ParcelStatus.DELIVERED);
    }
}