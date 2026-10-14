package modernjavainaction.chap05;
import java.util.*;

public class LowestDifference {
	
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        
        
        int input0 = sc.nextInt();
        
        sc.nextLine();
        
        String input = sc.nextLine().trim();
        
        

        String[] arr = input.split("\\s+");
        
        

        int[] a = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) 
        {
        	a[i] = Integer.parseInt(arr[i]);
        }
        
        
       
        int minDiff = Integer.MAX_VALUE;
        int index = -1;
        
        
        
        for (int i = 0; i < a.length - 1; i++) 
        {
            int d = Math.abs(a[i] - a[i + 1]);
            
            if (d < minDiff) 
            {
                minDiff = d;
                index = i;
            }
        }
        
        

        System.out.println("The given input string is: {" + input + "}");
        System.out.println("The lowest difference element is at index: " + index);
        System.out.println("The lowest difference is: " + minDiff);
    }
}
