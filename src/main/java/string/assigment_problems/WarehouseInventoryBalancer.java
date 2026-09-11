package string.assigment_problems;

import java.util.Scanner;

public class WarehouseInventoryBalancer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size: ");
        int n = sc.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];

        System.out.println("Enter Section A quantities:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println("Enter Section B quantities:");
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        int totalA = 0;
        int totalB = 0;

        for (int i = 0; i < n; i++) {
            totalA = totalA + a[i];
            totalB = totalB + b[i];
        }

        if (totalA == totalB) {
            System.out.println("Status: Balanced");
        } else {
            System.out.println("Status: Not Balanced");
        }

        int highest = a[0];
        String section = "Section A";
        int index = 0;

        for (int i = 0; i < n; i++) {

            if (a[i] > highest) {
                highest = a[i];
                section = "Section A";
                index = i;
            }

            if (b[i] > highest) {
                highest = b[i];
                section = "Section B";
                index = i;
            }
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);
        System.out.println("Highest Quantity: " + highest);
        System.out.println("Section: " + section);
        System.out.println("Item: " + (index + 1));

        sc.close();
    }
}