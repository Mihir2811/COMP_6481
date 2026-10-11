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
import java.time.LocalDate;

public class main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// input would be 5 28112020 28112022
		String input = sc.nextLine();
		
		// split would be [5] [28112020] [28112022]
		String[] parts = input.split(" ");
		
		// first split to books taken
		int books = Integer.parseInt(parts[0]);
		
		// second split to when books where taken
		String taken = parts[1];
		
		//third split to know when books where returned
		String returned = parts[2];
		
		
		// extract day month and year from taken input string
		int takenday = Integer.parseInt(taken.substring(0,2));
		int takenmonth = Integer.parseInt(taken.substring(2,4));
		int takenyear = Integer.parseInt(taken.substring(4,8));
		
		
		// extract day month and year from returned input string
		int returnedday = Integer.parseInt(returned.substring(0,2));
		int returnedmonth = Integer.parseInt(returned.substring(2,4));
		int returnedyear = Integer.parseInt(returned.substring(4,8));
		
		
		// checking condition to know that does the books taken are inside the allowed limit
		if(books <= 0 || books > 30) {
			System.out.println("Not allowed");
		}
		
		// formating dates to LocalDate format to find out does it fall in specified condition or not
		LocalDate takendate = LocalDate.of(takenyear, takenmonth, takenday);
		LocalDate returndate = LocalDate.of(returnedyear, returnedmonth, returnedday);
		
		LocalDate deadline = takendate.plusYears(2);
		
		if(returndate.isAfter(deadline)) {
			System.out.println("late");
		}
		else {
			System.out.println("on time");
		}
		
	}

}
