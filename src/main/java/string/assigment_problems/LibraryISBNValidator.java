package string.assigment_problems;

import java.util.Scanner;

public class LibraryISBNValidator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ISBN code: ");
        String code = sc.nextLine();

        code = code.trim();

        if (code.length() != 13) {
            System.out.println("Invalid: wrong length");
        }
        else {

            String publisher = code.substring(0, 3).toUpperCase();
            code = publisher + code.substring(3);

            boolean valid = true;

            for (int i = 0; i < 3; i++) {
                if (!Character.isLetter(code.charAt(i))) {
                    valid = false;
                }
            }

            for (int i = 3; i < 13; i++) {
                if (!Character.isDigit(code.charAt(i))) {
                    valid = false;
                }
            }

            if (!valid) {
                System.out.println("Invalid ISBN code");
            }
            else {

                String year = code.substring(3, 7);
                String catalog = code.substring(7);

                StringBuilder result = new StringBuilder();

                result.append("[");
                result.append(publisher);
                result.append("] YEAR: ");
                result.append(year);
                result.append(" | CATALOG: ");
                result.append(catalog);

                System.out.println(result);
            }
        }

        sc.close();
    }
}