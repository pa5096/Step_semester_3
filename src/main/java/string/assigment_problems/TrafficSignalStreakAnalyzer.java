package string.assigment_problems;

import java.util.Scanner;

public class TrafficSignalStreakAnalyzer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter signal sequence: ");
        String signal = sc.nextLine();

        int current = 1;
        int longest = 1;
        char longestColor = signal.charAt(0);

        for (int i = 1; i < signal.length(); i++) {

            if (signal.charAt(i) == signal.charAt(i - 1)) {
                current++;
            } else {
                current = 1;
            }

            if (current > longest) {
                longest = current;
                longestColor = signal.charAt(i);
            }
        }

        System.out.println("Longest Streak: " + longestColor + " = " + longest);

        sc.close();
    }
}