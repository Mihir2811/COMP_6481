/*
Finds the minimum distance between two identical characters in a given word
*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();

        int min = Integer.MAX_VALUE;

        boolean found = false;

        for (int i = 0; i < word.length(); i++) {

            for (int j = i + 1; j < word.length(); j++) {

                if (word.charAt(i) == word.charAt(j)) {

                    int difference = j - i - 1;

                    if (difference < min) {
                        min = difference;
                    }

                    found = true;
                }
            }
        }

        if (found) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }

        sc.close();
    }
}
