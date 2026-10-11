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
