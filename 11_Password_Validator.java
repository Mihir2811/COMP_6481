/*
🟡 Question 1 — String | Password Validator

A system checks whether a password is valid.

Input: A single string (the password)

Rules:

Must be at least 8 characters long
Must contain at least one digit
Must contain at least one uppercase letter
Must contain at least one lowercase letter

Print Valid if all rules pass, otherwise print Invalid followed by the first rule it breaks (only one message):

Too short
No digit
No uppercase
No lowercase

*/

import java.util.*;

public class test1{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        if(input.length()>8)
        {
            boolean check_digit = false;
            boolean uppercase_check = false;
            boolean lowercase_check = false;

            for(int i=0;i<input.length();i++)
            {
                if (Character.isDigit(input.charAt(i)))
                {
                    check_digit = true;
                }

                if(Character.isUpperCase(input.charAt(i)))
                {
                    uppercase_check = true;
                }

                if(Character.isLowerCase(input.charAt(i)))
                {
                    lowercase_check = true;
                }
            }

            if(check_digit && uppercase_check && lowercase_check)
            {
                System.out.println("Valid");
            }

            if(!check_digit) System.out.println("Invalid - No digit");
            else if(!uppercase_check) System.out.println("Invalid - No uppercase");
            else if(!lowercase_check) System.out.println("Invalid - No lowercase");
        }
        
        else
        {
            System.out.println("Invalid: Too short");
        }
    }
}
