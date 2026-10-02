package oop.class_problems;

abstract class Vehicle {
    private String name;
    private boolean isRented;

    public Vehicle(String name) {
        this.name = name;
        this.isRented = false;
    }

    public String getName() {
        return name;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        this.isRented = rented;
    }

    public abstract double calculateRentalCharge(int days);
}

class StandardCar extends Vehicle {
    public StandardCar(String name) {
        super(name);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 50.0;
    }
}

class LuxuryCar extends Vehicle {
    public LuxuryCar(String name) {
        super(name);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 100.0;
    }
}

class RentalService {
    public void rentVehicle(Vehicle vehicle, int days) {
        if (vehicle.isRented()) {
            System.out.println("Rental failed: " + vehicle.getName() + " is currently rented.");
            return;
        }
        vehicle.setRented(true);
        double totalCharge = vehicle.calculateRentalCharge(days);
        System.out.printf("%s rented for %d days. Total charge: $%.2f%n", vehicle.getName(), days, totalCharge);
    }

    public void returnVehicle(Vehicle vehicle) {
        if (!vehicle.isRented()) {
            System.out.println(vehicle.getName() + " is not currently rented.");
            return;
        }
        vehicle.setRented(false);
        System.out.println(vehicle.getName() + " returned. Now available.");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        Vehicle luxuryCar = new LuxuryCar("Luxury Car A");
        Vehicle standardCar = new StandardCar("Standard Car B");

        service.rentVehicle(luxuryCar, 3);
        service.rentVehicle(standardCar, 5);
        service.returnVehicle(luxuryCar);
    }
}