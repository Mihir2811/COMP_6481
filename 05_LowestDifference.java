/*
 * Given a list of elements:
 *
 * {30, 40, 50, 51, 60}
 *
 * For each element, calculate the difference between that element
 * and the element immediately next to it.
 *
 * The difference should be calculated consecutively for all
 * neighboring elements.
 *
 * For example:
 *
 * 30 and 40 -> difference = 10
 * 40 and 50 -> difference = 10
 * 50 and 51 -> difference = 1
 * 51 and 60 -> difference = 9
 *
 * Find the lowest difference value.
 *
 * If multiple pairs have the same lowest difference, return the
 * index of the first element of the first pair.
 *
 * For the given list:
 *
 * Differences:
 * Index 0 -> |30 - 40| = 10
 * Index 1 -> |40 - 50| = 10
 * Index 2 -> |50 - 51| = 1
 * Index 3 -> |51 - 60| = 9
 *
 * The lowest difference is 1.
 *
 * Therefore, the index of the first element is:
 *
 * 2
 *
 * Expected Output:
 * 2
 */
 */

import java.util.*;

public class ProblemA2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
//		String input = "30 40 50 51 60";
		String input = sc.nextLine();
		
		String[] arr = input.split(" ");
		
		int diff = 0;
		int index = 0;
		
		if(Integer.valueOf(arr[0])>Integer.valueOf(arr[1]))
		{
			diff = Integer.valueOf(arr[0]) - Integer.valueOf(arr[1]);
		}
		
		else if(Integer.valueOf(arr[1])>Integer.valueOf(arr[0]))
		{
			diff = Integer.valueOf(arr[1]) - Integer.valueOf(arr[0]);
		}
		
		for(int i=0; i<(arr.length-1); i++)
		{
			if(Integer.valueOf(arr[i])>Integer.valueOf(arr[i+1]))
			{
				if((Integer.valueOf(arr[i])-Integer.valueOf(arr[i+1]))<diff)
				{
					diff = Integer.valueOf(arr[i])-Integer.valueOf(arr[i+1]);
					index = i;
				}
			}
			
			else if (Integer.valueOf(arr[i+1])>Integer.valueOf(arr[i])) 
				{
					if((Integer.valueOf(arr[i+1])-Integer.valueOf(arr[i]))<diff)
					{
						diff = Integer.valueOf(arr[i+1])-Integer.valueOf(arr[i]);
						index = i;
					}
				}
			
			else 
			{
				continue;
			}
		}
		
//		System.out.println(index);
		System.out.println("The given input string is: "+ "{" +input+"}");
		System.out.println("The lowest difference element is at index: "+index);
		System.out.println("The lowest difference is: "+diff);

	}

}
