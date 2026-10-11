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
