package string.class_problems;

import java.util.Scanner;

public class CSVStudentRecordParser {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student record: ");
        String record = sc.nextLine();

        String[] parts = record.split(",");

        if (parts.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Name: " + parts[0]);
            System.out.println("Roll No: " + parts[1]);
            System.out.println("Dept: " + parts[2]);
        }

        sc.close();
    }
}