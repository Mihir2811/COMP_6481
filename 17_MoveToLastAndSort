
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        int rotations = 0;

        while (rotations < str.length()) {

            boolean sorted = true;

            for (int i = 0; i < str.length() - 1; i++) {
                if (alphabet.indexOf(str.charAt(i)) >
                    alphabet.indexOf(str.charAt(i + 1))) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                System.out.println("This situation makes \"" + str + "\"");
                break;
            }

            str = str.substring(1) + str.charAt(0);
            rotations++;
        }

        if (rotations == str.length() && !isSorted(str, alphabet)) {
            System.out.println("Cannot be sorted by rotation.");
        }

        sc.close();
    }

    static boolean isSorted(String str, String alphabet) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (alphabet.indexOf(str.charAt(i)) >
                alphabet.indexOf(str.charAt(i + 1))) {
                return false;
            }
        }
        return true;
    }
}
