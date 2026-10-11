/*
 * Problem Statement
 *
 * A library allows users to borrow a certain number of books.
 * The program receives the number of books borrowed, the date the books
 * were borrowed, and the date they were returned.
 *
 * Input Format:
 * A single line containing three space-separated values:
 *
 * Number of books
 * Borrow date in DDMMYYYY format
 * Return date in DDMMYYYY format
 *
 * Rules & Conditions:
 *
 * 1. The number of books must be greater than 0 and no more than 30.
 *    If the number is outside this range, print:
 *
 *    Not allowed
 *
 * 2. A book must be returned within 2 years from the borrowing date.
 *
 * 3. If the return date is after the 2-year deadline, print:
 *
 *    late
 *
 * 4. If the return date is on or before the 2-year deadline, print:
 *
 *    on time
 *
 * Example:
 *
 * Input:
 * 5 28112020 28112022
 *
 * Output:
 * on time
 *
 * Example:
 *
 * Input:
 * 5 28112020 29112022
 *
 * Output:
 * late
 */

import java.util.Scanner;

public class main

{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();
		
		input = input.toUpperCase().replaceAll("-", "");
		
		int i;
		
		for (i=0; i<input.length(); i++)
		{
			if(!Character.isLetter(input.charAt(i))) {
				break;
			}
			
		}
		
		String letter = input.substring(0,i);
		String number = input.substring(i);
		
		String result = letter + "-" + number;

		System.out.println(result);
		
	}
}
