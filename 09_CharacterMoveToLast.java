import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        String[] original_words = new String[n];
        String[] test_words = new String[n];

        // Take input for all test cases
        for (int i = 0; i < n; i++) {
            original_words[i] = sc.next();
            test_words[i] = sc.next();
        }

        // Check each test case
        for (int i = 0; i < n; i++) {

            char[] testing1 = original_words[i].toCharArray();
            char[] testing2 = test_words[i].toCharArray();

            boolean found = false;

            // Rotate the test word
            for (int j = 0; j < testing2.length; j++) {

                String test1 = new String(testing1);
                String test2 = new String(testing2);

                if (test1.equals(test2)) {
                    found = true;
                    break;
                }

                // Save the first character
                char first = testing2[0];

                // Move everything one position to the left
                for (int k = 0; k < testing2.length - 1; k++) {
                    testing2[k] = testing2[k + 1];
                }

                // Put the first character at the end
                testing2[testing2.length - 1] = first;
            }

            if (found) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}
