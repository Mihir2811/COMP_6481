// InsertionSort.java

public class InsertionSort {

    public static void insertionSort(int[] arr) 
    {
        int n = arr.length;

        for (int i = 1; i < n; i++) 
        {
            // 64, 25, 12, 22, 11
            int key = arr[i];   // the element we're currently placing
            // key = 25
            
            int j   = i - 1;   // start comparing from the element just before
            // j = 0

            // Shift elements that are GREATER than key one position to the right
            // 0>=0 && 64>25
            while (j >= 0 && arr[j] > key) 
            {
                arr[j + 1] = arr[j];  // shift right
                //arr[1] = 64
                // 64,64,12,22,11
                j--;
                //j=-1
            }

            // Place key in its correct sorted position
            arr[j + 1] = key;
            // arr[0] = 25
            // 25,64,12,22,11

            // Show progress after each pass
            System.out.print("After pass " + i + " (key=" + key + "): ");

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

        System.out.println("Original: ");
        printArray(arr);
        System.out.println();

        insertionSort(arr);

        System.out.println("\nSorted: ");
        printArray(arr);
    }
}
