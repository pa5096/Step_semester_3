package string.class_problems;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of team members: ");
        int n = sc.nextInt();

        String[] name = new String[n];
        double[] weight = new double[n];
        double[] height = new double[n];
        double[] bmi = new double[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nMember " + (i + 1));

            System.out.print("Enter name: ");
            name[i] = sc.next();

            System.out.print("Enter weight in kg: ");
            weight[i] = sc.nextDouble();

            System.out.print("Enter height in meters: ");
            height[i] = sc.nextDouble();

            bmi[i] = weight[i] / (height[i] * height[i]);
        }

        System.out.println("\nBMI Results");

        for (int i = 0; i < n; i++) {

            System.out.print(name[i] + " - BMI: " + bmi[i]);

            if (bmi[i] < 18.5) {
                System.out.println(" - Underweight");
            }
            else if (bmi[i] < 25) {
                System.out.println(" - Normal");
            }
            else if (bmi[i] < 30) {
                System.out.println(" - Overweight");
            }
            else {
                System.out.println(" - Obese");
            }
        }

        sc.close();
    }
}