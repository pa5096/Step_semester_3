package string.class_problems;

import java.util.Scanner;

public class FileExtensionValidator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        int dot = filename.lastIndexOf('.');

        String extension = filename.substring(dot + 1);

        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {

            System.out.println("Accepted");

        } else {
            System.out.println("Rejected — invalid file type");
        }

        sc.close();
    }
}