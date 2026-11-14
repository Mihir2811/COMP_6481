/*
 * Problem Statement
 * ------------------------------------------------------------------------------------------------------------------------------------
 * An element can move in four direction N S E W.
 * For one input it can only move 4 units
 * At the end the robot should reach to its home position
 * 
 * Solution Approach
 * ------------------------------------------------------------------------------------------------------------------------------------
 * Take user input for total test cases
 * Record all user specified test cases in an array
 * Initialize 4 int values as 0
 * Initialize 4 char values as N S E W
 * 
 * Start with first test case
 * Collect the characters of the given input string
 * Compare with char values if matches increment the value of respective element by 1
 * After iterating from all the characters check that all the values are the same or not 
 * If not store 0 
 * Els if all are same then store 1
 * 
 */


import java.util.*;

public class Problem5A {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int total_inputs = sc.nextInt();
        sc.nextLine();

        String[] input_list = new String[total_inputs];

        for(int i = 0; i < total_inputs; i++)
        {
            input_list[i] = sc.nextLine().toUpperCase();
        }

        String result1 = "";

        for(int j = 0; j < total_inputs; j++)
        {
            int N = 0;
            int S = 0;
            int E = 0;
            int W = 0;

            String word = input_list[j];

            for(int k = 0; k < word.length(); k++)
            {
                char direction = word.charAt(k);

                if(direction == 'N')
                {
                    N++;
                }
                else if(direction == 'S')
                {
                    S++;
                }
                else if(direction == 'E')
                {
                    E++;
                }
                else if(direction == 'W')
                {
                    W++;
                }
            }

            if(N == S && E == W)
            {
                result1 = result1 + "1";
            }
            else
            {
                result1 = result1 + "0";
            }
        }

        System.out.println(result1);

        sc.close();
    }
}
