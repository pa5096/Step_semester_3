package string.class_problems;

import java.util.Scanner;

public class MaskedPhoneNumberFormatter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        if (phone.length() != 10) {
            System.out.println("Invalid phone number");
        } else {

            String last4 = phone.substring(6);

            StringBuilder result = new StringBuilder("XXXXXX");
            result.append("-");
            result.append(last4);

            System.out.println(result);
        }

        sc.close();
    }
}
