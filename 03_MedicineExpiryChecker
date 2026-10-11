/*
 * Problem Statement
A pharmacy receives batches of medication. Different types of medication have different shelf lives. You need to write a program that reads the batch ID, the manufacture date, and today's date to determine if the medication is safe to use or needs to be thrown away.

Input Format
A single line with three space-separated values:

Batch ID (A string)

Manufacture Date in DDMMYYYY format

Current Date in DDMMYYYY format

Rules & Conditions
Determine the Shelf Life: The shelf life depends on the first letter of the Batch ID:

If it starts with "Y" (e.g., Y-402), it expires in 2 years.

If it starts with "M" (e.g., M-109), it expires in 6 months.

If it starts with "D" (e.g., D-882), it expires in 45 days.

If it starts with any other letter, print Invalid Batch and stop.

Calculate Expiration: Add the correct shelf life to the Manufacture Date to get the Expiration Date.

Output Messages:

If the Current Date is strictly after the Expiration Date, print Dispose.

If the Current Date is on or before the Expiration Date, print Safe to use.

Example Input & Output
Example 1
Input: M-109 01012024 15062024

Output: Safe to use

(Logic: Starts with M -> 6 months. Expires 01/07/2024. Current date is before that.)

Example 2
Input: D-882 01012024 20022024

Output: Dispose

(Logic: Starts with D -> 45 days. Expires 15/02/2024. Current date is after that.)

Example 3
Input: X-999 01012024 01022024

Output: Invalid Batch
 */

import java.time.LocalDate;
import java.util.Scanner;

public class Pharmacy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// M-109 01012024 15062024
		String input = sc.nextLine();
		
		input = input.toUpperCase();
		
		input = input.replaceAll("-", "");
		
		//split input
		String[] split = input.split(" ");
		
		String taken = split[1];
		
		String returned = split[2];
		
		String temp = split[0];
		
		int i;
		
		for(i=0;i<temp.length();i++) 
		{
			if(!Character.isLetter(temp.charAt(i))) 
			{
				break;
			}
				
		}
		
		String shelflifecode = temp.substring(0,i);

		
		int takenday = Integer.parseInt(taken.substring(0,2));
		int takenmonth = Integer.parseInt(taken.substring(2,4));
		int takenyear = Integer.parseInt(taken.substring(4,8));
		
		int returnday = Integer.parseInt(returned.substring(0,2));
		int returnmonth = Integer.parseInt(returned.substring(2,4));
		int returnyear = Integer.parseInt(returned.substring(4,8));
		
		LocalDate returndate = LocalDate.of(returnyear, returnmonth, returnday);
		LocalDate takendate = LocalDate.of(takenyear, takenmonth, takenday);
		
		if(shelflifecode.equals("Y"))
		{
			LocalDate Deadline = takendate.plusYears(2);
			
			if(returndate.isAfter(Deadline)) {
				System.out.println("Dispose");
			}
			else {
				System.out.println("Safe to use");
			}
		}
		
		else if(shelflifecode.equals("M")) {
			LocalDate Deadline = takendate.plusMonths(6);
			
			if(returndate.isAfter(Deadline)) {
				System.out.println("Dispose");
			}
			else {
				System.out.println("Safe to use");
			}
			
		}
		
		else if(shelflifecode.equals("D")) {
			LocalDate Deadline = takendate.plusDays(45);
			
			if(returndate.isAfter(Deadline)) {
				System.out.println("Dispose");
			}
			else {
				System.out.println("Safe to use");
			}
			
		}
		else {
			System.out.println("Invalid Batch");
		}
		
		
	}

}
