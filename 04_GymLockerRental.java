/*
 * Problem Statement
 *
 * A gym allows customers to rent lockers for a maximum period of 6 months.
 * The program receives the number of lockers rented, the rental start date,
 * and the return date.
 *
 * Input Format:
 * A single line containing three space-separated values:
 *
 * Number of lockers
 * Rental start date in DDMMYYYY format
 * Return date in DDMMYYYY format
 *
 * Rules & Conditions:
 *
 * 1. The number of lockers must be between 0 and 10.
 *    If the number is less than 0 or greater than 10, print:
 *
 *    Invalid rental
 *
 * 2. If the locker rental is valid, calculate the deadline by adding
 *    6 months to the rental start date.
 *
 * 3. If the return date is after the 6-month deadline, print:
 *
 *    Overdue
 *
 * 4. If the return date is on or before the 6-month deadline, print:
 *
 *    Within limit
 *
 * Example:
 *
 * Input:
 * 3 01012024 01072024
 *
 * Output:
 * Within limit
 *
 * Example:
 *
 * Input:
 * 3 01012024 02072024
 *
 * Output:
 * Overdue
 *
 * Example:
 *
 * Input:
 * 11 01012024 01072024
 *
 * Output:
 * Invalid rental
 */

import java.time.LocalDate;
import java.util.Scanner;
public class gymlocker {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 3 01012024 01072024
		String input = sc.nextLine();
		
		//split input
		String[] split = input.split(" ");
		
		int locker = Integer.parseInt(split[0]);
		
		String taken = split[1];
		
		String returned = split[2];
		
		if (locker<0 || locker>10) {
			System.out.println("Invalid rental");
		}
		else {
			
		int takenday = Integer.parseInt(taken.substring(0,2));
		int takenmonth = Integer.parseInt(taken.substring(2,4));
		int takenyear = Integer.parseInt(taken.substring(4,8));
		
		int returnday = Integer.parseInt(returned.substring(0,2));
		int returnmonth = Integer.parseInt(returned.substring(2,4));
		int returnyear = Integer.parseInt(returned.substring(4,8));
		
		LocalDate returndate = LocalDate.of(returnyear, returnmonth, returnday);
		LocalDate takendate = LocalDate.of(takenyear, takenmonth, takenday);
		
		LocalDate Deadline = takendate.plusMonths(6);
		
		if(returndate.isAfter(Deadline)) {
			System.out.println("Overdue");
		}
		else {
			System.out.println("Within limit");
		}
		}
	}

}
