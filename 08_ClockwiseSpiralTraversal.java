import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of test cases:");
        int n = sc.nextInt();

        for (int t = 0; t < n; t++) {

            System.out.println("Enter number of rows and columns:");
            int rows = sc.nextInt();
            int cols = sc.nextInt();

            int[][] matrix = new int[rows][cols];

            System.out.println("Enter the matrix values row by row:");

            for (int i = 0; i < rows; i++) {
                System.out.println("Enter row " + (i + 1) + ":");

                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            int top = 0;
            int bottom = rows - 1;
            int left = 0;
            int right = cols - 1;

            System.out.println("Clockwise traversal:");

            while (top <= bottom && left <= right) {

                // Move from left to right
                for (int j = left; j <= right; j++) {
                    System.out.print(matrix[top][j] + " ");
                }
                top++;

                // Move from top to bottom
                for (int i = top; i <= bottom; i++) {
                    System.out.print(matrix[i][right] + " ");
                }
                right--;

                // Move from right to left
                if (top <= bottom) {
                    for (int j = right; j >= left; j--) {
                        System.out.print(matrix[bottom][j] + " ");
                    }
                    bottom--;
                }

                // Move from bottom to top
                if (left <= right) {
                    for (int i = bottom; i >= top; i--) {
                        System.out.print(matrix[i][left] + " ");
                    }
                    left++;
                }
            }

            System.out.println();
        }

        sc.close();
    }
}
