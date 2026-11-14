/*
 * Statement
 * ------------------------------------------------------------------------------------------------------------------------------------
 * Input string should match another input string in which there might be an extra character inserted into it
 * if removing that extra character both the string matches then print test case is pass for that element or else fail.
 * 
 * Approach
 * ------------------------------------------------------------------------------------------------------------------------------------
 * Take total input test cases number
 * Take the specified number of tests input strings and store
 * Pick the first input
 * Split the input using space and store elements in individual strings named as original and modified
 * Compare length of modified string with original string if the difference is not more than 1 continue or else store 0
 * If both words are the same store 0
 * Compare individual character of both the words original and modified
 * If both are the same continue to next one
 * If not same move to next character index element of the modified word
 * After removing compare individual elements again
 * If all pass print 1 or else sti 0
 */


import java.util.*;

public class ProblemB {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int total_inputs = sc.nextInt();
        sc.nextLine();

        String[] test_cases = new String[total_inputs];

        ArrayList<String> output = new ArrayList<>();

        for(int i = 0; i < total_inputs; i++)
        {
            test_cases[i] = sc.nextLine().toUpperCase();
        }

        for(int i = 0; i < total_inputs; i++)
        {
            String[] input_split = test_cases[i].split(" ");

            String original = input_split[0];
            String modified = input_split[1];

            int original_length = original.length();
            int modified_length = modified.length();

            boolean case_result = false;

            if(modified_length - original_length == 1)
            {
                int j = 0;
                boolean extra_character_found = false;
                boolean valid = true;

                for(int k = 0; k < original_length; k++)
                {
                    if(original.charAt(k) == modified.charAt(j))
                    {
                        j++;
                    }
                    else
                    {
                        if(extra_character_found == false)
                        {
                            extra_character_found = true;
                            j++;
                        }
                        else
                        {
                            valid = false;
                            break;
                        }
                    }
                }

                if(valid)
                {
                    case_result = true;
                }
            }

            if(case_result)
            {
                output.add("1");
            }
            else
            {
                output.add("0");
            }
        }

        System.out.println(output);

        sc.close();
    }
}
