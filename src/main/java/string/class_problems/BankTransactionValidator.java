package string.class_problems;

import java.util.Scanner;

public class BankTransactionValidator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");
        String ref = sc.nextLine();

        if (ref.startsWith("TXN") &&
            ref.endsWith("IN") &&
            ref.length() == 10) {

            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        sc.close();
    }
}