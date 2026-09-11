package string.class_problems;

import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] computer = {"Scissors", "Paper", "Rock", "Scissors", "Paper"};

        int win = 0;
        int loss = 0;
        int draw = 0;

        for (int i = 0; i < 5; i++) {

            System.out.print("Enter Rock, Paper or Scissors: ");
            String player = sc.next();

            System.out.println("Computer: " + computer[i]);

            if (player.equals(computer[i])) {
                System.out.println("Draw");
                draw++;
            }
            else if (player.equals("Rock") && computer[i].equals("Scissors")) {
                System.out.println("Player Wins");
                win++;
            }
            else if (player.equals("Paper") && computer[i].equals("Rock")) {
                System.out.println("Player Wins");
                win++;
            }
            else if (player.equals("Scissors") && computer[i].equals("Paper")) {
                System.out.println("Player Wins");
                win++;
            }
            else {
                System.out.println("Computer Wins");
                loss++;
            }
        }

        System.out.println("\nFinal Score");
        System.out.println("Wins: " + win);
        System.out.println("Losses: " + loss);
        System.out.println("Draws: " + draw);
        System.out.println("Win Percentage: " + (win * 100.0 / 5) + "%");

        sc.close();
    }
}