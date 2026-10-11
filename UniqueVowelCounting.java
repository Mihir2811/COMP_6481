/*
 * Problem Statement
 *
 * Given a word, find the number of unique vowels present in the word.
 *
 * The vowels are:
 *
 * a, e, i, o, u
 *
 * The program should:
 *
 * 1. Read or define a word.
 * 2. Convert the word to lowercase so that uppercase and lowercase
 *    letters are treated the same.
 * 3. Check each character of the word to determine whether it is a vowel.
 * 4. If the character is a vowel, check whether that vowel has already
 *    been found.
 * 5. Count the vowel only if it has not been found before.
 * 6. Store each unique vowel in an array.
 * 7. Print the total number of unique vowels.
 *
 * Only arrays should be used to keep track of the vowels that have
 * already been found.
 *
 * Example 1:
 *
 * Input:
 * Education
 *
 * Unique vowels:
 * e, u, a, i, o
 *
 * Output:
 * Unique vowels: 5
 *
 *
 * Example 2:
 *
 * Input:
 * Banana
 *
 * Unique vowels:
 * a
 *
 * Output:
 * Unique vowels: 1
 *
 *
 * Example 3:
 *
 * Input:
 * Hello
 *
 * Unique vowels:
 * e, o
 *
 * Output:
 * Unique vowels: 2
 */

public class main {

    public static void main(String[] args) {
    	
    	// I will use a counter to identify how many unique vowels I have found.
        int count = 0;

        //Test Word I am using to count vowel.
        String word = "Education";

        // Converting to lowercase to remove the problem of case mismatch
        String[] parts = word.toLowerCase().split("");

        // Array of vowels
        String[] vowels = {"a", "e", "i", "o", "u"};

        // Array of found elements
        String[] found_elements = new String[parts.length];
        
        // Index number for found vowels
        int found_index = 0;

        for (int i = 0; i < parts.length; i++) 
        {

            // Check whether the current character is a vowel
            for (int j = 0; j < vowels.length; j++) {
            	
            	// Because it is a string I will use .equals instead of =
                if (parts[i].equals(vowels[j])) {

                	// Boolean to check already found or not
                    boolean alreadyFound = false;

                    // Check whether we have already counted this vowel
                    for (int k = 0; k < found_index; k++) 
                    {
                        if (parts[i].equals(found_elements[k])) 
                        {
                            alreadyFound = true;
                            break;
                        }
                    }

                    // Count the vowel only if it hasn't been found before
                    if (!alreadyFound) {
                        count++;
                        found_elements[found_index] = parts[i];
                        found_index++;
                    }

                    break;
                }
            }
        }

        System.out.println("Unique vowels: " + count);
    }
}
