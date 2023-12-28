// Lyla Shami, CS 211, Craig Niiyama, 5-13-2022

public class BogoSort {

    public static void main(String[] args) {


        int[] myArray = {11, 12, 13, 10};

        long start = System.currentTimeMillis();
        bogoSort(myArray);

        long end = System.currentTimeMillis();
        long total = end - start;

        System.out.println(total);
    }

    // Places the elements of a into sorted order.
    public static void bogoSort(int[] a) {
        while (isSorted(a) == false) { //if the array is not sorted
            printArray(a);
            shuffle(a);//  calling the shuffle method, shuffling through the array
        }

    }

    // Returns true if a's elements are in sorted order.
    public static boolean isSorted(int[] a) {
        boolean flag = true; //temp value
        for (int i = 0; i < a.length - 1; i++) { // iterating through the array
            if (a[i] < a[i + 1]) { //check if the element at i is less than the element at next position
                flag = true; //if the statement is true, flag is set to true
            } else {
                flag = false; //if the statement is false, the flag is set to false
                break; //if false the loops breaks
            }
        }
        return flag; // return the value

    }


    // Shuffles an array of ints by randomly swapping each
    // element with an element ahead of it in the array.
    public static void shuffle(int[] a) {
        for (int i = 0; i < a.length - 1; i++) { //iterating through the array
            swap(a, i, i + 1); //calling the swap method and swapping elements
        }


    }

    // Swaps a[i] with a[j].
    public static void swap(int[] a, int i, int j) {
        int temp = a[i]; //temporary variable
        a[i] = a[j]; //swapping the elements at the position of j and i
        a[j] = temp; //setting the element at position j as the temp value

    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
    }


}//end of BogoSort class






