
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of doors: ");
        int n = sc.nextInt();

        boolean[] doors = new boolean[n + 1];

        // Each round toggles the corresponding doors
        for (int i = 1; i <= n; i++) {

            for (int j = i; j <= n; j += i) {
                doors[j] = !doors[j];
            }

        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (doors[i]) {
                count++;
            }
        }

        System.out.println("Number of open doors: " + count);

        sc.close();
    }
}


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of doors: ");
        int n = sc.nextInt();

        boolean[] doors = new boolean[n + 1];

        // Each round toggles the corresponding doors
        for (int i = 1; i <= n; i++) {

            for (int j = i; j <= n; j += i) {
                doors[j] = !doors[j];
            }

        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (doors[i]) {
                count++;
            }
        }

        System.out.println("Number of open doors: " + count);

        sc.close();
    }
}
