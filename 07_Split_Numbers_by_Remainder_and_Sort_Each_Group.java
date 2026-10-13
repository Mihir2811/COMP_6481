/**
 * This class is designed to take an array of numbers as input from the user and
 * group them based on the remainder when divided by K. The groups are then sorted
 * and printed to the console.
 */

package modernjavainaction.chap05;

import java.util.*;

public class Split_Numbers_by_Remainder_and_Sort_Each_Group {

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        // Reading the array of numbers
	        String input = sc.nextLine();

	        String[] parts = input.split(" ");

	        // Reading K
	        int K = sc.nextInt();

	        // Create K groups with 2d approach where i take a arraylist within another arraylist
	        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();

	        // Running loop to generate K number of groups
	        for (int i = 0; i < K; i++) 
	        {
	            groups.add(new ArrayList<>());
	        }

	        // Putting each number into its correct group
	        for (String part : parts)
	        {
	            int number = Integer.parseInt(part);

	            // Finding the remainder
	            int remainder = number % K;

	            if(remainder<0) 
	            {
	            	remainder = remainder*(-1);
	            	// Adding number to the remainder group
	            	groups.get(remainder).add(number);
	            }
	            else
	            	groups.get(remainder).add(number);
	        }

	        // Sort and print each group
	        for (int i = 0; i < K; i++) 
	        
	        {
	            ArrayList<Integer> group = groups.get(i);

	            // If the group is empty
	            if (group.isEmpty()) 
	            {
	                System.out.println("None");
	            }
	            
	            else 
	            {
	                // Group 0 would be sorted as descending
	                if (i == 0) 
	                {
	                    group.sort(Collections.reverseOrder());
	                }

	                // Other groups would be sorted ascending
	                else 
	                {
	                    Collections.sort(group);
	                }

	                // Printing the numbers
	                for (int number : group) 
	                {
	                    System.out.print(number + " ");
	                }

                    System.out.println("\n");
	            }
	        }

	        sc.close();
	    }
	}
