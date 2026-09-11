package string.assigment_problems;

import java.util.Scanner;

public class ProductInventoryCSVParser {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product record: ");
        String record = sc.nextLine();

        String[] parts = record.split(",");

        if (parts.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Product: " + parts[0]);
            System.out.println("SKU: " + parts[1]);
            System.out.println("Qty: " + parts[2]);
        }

        sc.close();
    }
}