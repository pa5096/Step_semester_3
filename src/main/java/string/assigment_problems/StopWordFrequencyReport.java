package string.assigment_problems;

import java.util.Scanner;

public class StopWordFrequencyReport {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String text = sc.nextLine();

        text = text.toLowerCase();
        text = text.replace(".", "");
        text = text.replace(",", "");

        String[] words = text.split("\\s+");

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        String[] unique = new String[words.length];
        int[] frequency = new int[words.length];

        int count = 0;

        for (int i = 0; i < words.length; i++) {

            boolean stop = false;

            for (int j = 0; j < stopWords.length; j++) {
                if (words[i].equals(stopWords[j])) {
                    stop = true;
                }
            }

            if (!stop) {

                int position = -1;

                for (int j = 0; j < count; j++) {
                    if (unique[j].equals(words[i])) {
                        position = j;
                    }
                }

                if (position == -1) {
                    unique[count] = words[i];
                    frequency[count] = 1;
                    count++;
                } else {
                    frequency[position]++;
                }
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.println(unique[i] + ": " + frequency[i]);
        }

        sc.close();
    }
}