/*
 * 🟡 Question 2 — Array | Exam Score Analyzer

Read n scores from a single space-separated line.

Print:

The highest score
The lowest score
The average (rounded to 2 decimal places)
How many scores are above average

Example:

Input:  85 90 72 60 95 88
Output:
Highest: 95
Lowest: 60
Average: 81.67
Above average: 3
 */

package modernjavainaction.chap02;

import java.util.*;

public class Exam_Score_Analyser {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] parts = input.split(" ");

        int highest_score = 0;
        int lowest_score = 0;
        int[] scores = new int[parts.length];

        for(int i=0; i<parts.length; i++)
        {
            if(Integer.parseInt(parts[i])>highest_score) highest_score = Integer.parseInt(parts[i]);
            if(Integer.parseInt(parts[i])<lowest_score) lowest_score = Integer.parseInt(parts[i]);

            scores[i] = Integer.parseInt(parts[i]);
        }

        double average = 0;

        for(int j=0; j<scores.length; j++)
        {
            average = scores[j] + average;
        }

        average = average / scores.length;
        
        int count_above_average = 0;

        for(int k=0;k<scores.length;k++)
        {
            if(scores[k]>average)
            {
                count_above_average++;
            }
        }

        System.out.println("Highest: " + highest_score);
        System.out.println("Lowest: " + lowest_score);
        System.out.printf("Average: %.2f%n", average);
        System.out.println("Above average: " + count_above_average);


    }
}
