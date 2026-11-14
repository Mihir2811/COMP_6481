
/*
 * Problem Statement
 * ---------------------------------------------------------
 * Two strings are provided.
 * The modified string contains exactly one extra character.
 *
 * If removing that extra character makes both strings equal,
 * print 1.
 * Otherwise, print 0.
 *
 * Example:
 * Original:  ABC
 * Modified: AXBC
 *
 * Remove X from AXBC:
 * ABC == ABC
 *
 * Output: 1
 */

import java.util.*;

public class ProblemB {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take the total number of test cases
        int total_inputs = sc.nextInt();
        sc.nextLine();

        // Store all test cases
        String[] test_cases = new String[total_inputs];

        // Store the final results
        ArrayList<String> output = new ArrayList<>();

        // Take input for every test case
        for(int i = 0; i < total_inputs; i++)
        {
            test_cases[i] = sc.nextLine().toUpperCase();
        }

        // Process each test case
        for(int i = 0; i < total_inputs; i++)
        {
            // Split the input into original and modified strings
            String[] input_split = test_cases[i].trim().split("\\s+");

            String original = input_split[0];
            String modified = input_split[1];

            // Calculate the lengths of both strings
            int original_length = original.length();
            int modified_length = modified.length();

            boolean case_result = false;

            // Modified string must have exactly one extra character
            if(modified_length == original_length + 1)
            {
                int j = 0;

                // Tracks whether an extra character was skipped
                boolean extra_character_found = false;

                // Tracks whether all remaining characters match
                boolean valid = true;

                // Compare characters from both strings
                for(int k = 0; k < original_length; k++)
                {
                    // Check whether the current characters match
                    if(original.charAt(k) == modified.charAt(j))
                    {
                        // Both characters match, move to next index
                        j++;
                    }
                    else
                    {
                        // A mismatch means we found the extra character
                        if(extra_character_found == false)
                        {
                            extra_character_found = true;

                            // Skip the extra character in modified
                            j++;
                        }
                        else
                        {
                            // More than one mismatch means invalid
                            valid = false;
                            break;
                        }
                    }
                }

                // If all characters match, the test case passes
                // The extra character may also be the last character
                if(valid && extra_character_found == false)
                {
                    case_result = true;
                }
                else if(valid)
                {
                    case_result = true;
                }
            }

            // Store the result
            if(case_result)
            {
                output.add("1");
            }
            else
            {
                output.add("0");
            }
        }

        // Print all results
        System.out.println(output);

        sc.close();
    }
}
