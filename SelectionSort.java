// SelectionSort.java

public class SelectionSort {

    public static void selectionSort(int[] arr) 
    {
        // arr = {64, 25, 12, 22, 11};
        int n = arr.length;
        // n = 5
        for (int i = 0; i < n - 1; i++) 
        {
            // i =0
            // Step 1: Assume the first unsorted element is the minimum
            int minIndex = i;
            // minIndex = 0

            // Step 2: Find the ACTUAL minimum in the unsorted part
            for (int j = i + 1; j < n; j++) 
            {
                // j = 1
                if (arr[j] < arr[minIndex]) 
                {
                    //25<64
                    minIndex = j;        // found a smaller value
                    // minIndex = 1
                }
            }

            // Step 3: Swap the found minimum with position i
            if (minIndex != i) // minindex =! 0
            {
                int temp       = arr[i];
                // temp = 64
                arr[i]         = arr[minIndex];
                // arr[0] = 25 (arr[1])
                arr[minIndex]  = temp;
                // arr[1] = 64
            }

            // 25,64,12,22,11

            // Show progress after each pass
            System.out.print("After pass " + (i + 1) + ": ");
            printArray(arr);
        }
    }

    public static void printArray(int[] arr) 
    {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    public static void main(String[] args) 
    {
        int[] arr = {64, 25, 12, 22, 11};

        System.out.println("Original:    ");
        printArray(arr);
        System.out.println();

        selectionSort(arr);

        System.out.println("\nSorted:      ");
        printArray(arr);
    }
}
