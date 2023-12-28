import java.util.Scanner;

/**
 * Lyla Shami
 * 5/06/2022
 * CS 211
 * RecursiveAssignment: This class is using a recursive method to calculate the number of permutations
 * of size r on the set of n
 */
public class recursiveAssignment {
    /**
     * Main method of the class
     * calls the recursive method to calculate the number of permutations
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        int n = getN(reader);

        int r = getR(reader, n);

        //finding the permutations of  length r from set n
        System.out.print("For n = " + n + ", and r = " + r + ", the number of unique permutations is " + permut(n, r) + ".");

    }

    private static int getR(Scanner reader, int n) {
        System.out.print("Please enter in the value of the size of the permutation (r). \nMust be less than " + n + ": ");
        int r = reader.nextInt();
        System.out.println();
        while (r >= n) {
            System.out.print("Please enter in the value of the size of the permutation (r). \nMust be less than " + n + ": ");
            r = reader.nextInt();
            System.out.println();
        }
        return r;
    }

    private static int getN(Scanner reader) {
        System.out.print("Please enter in the value of the size of the original set (n). \nMust be larger than 0: ");
        int n = reader.nextInt();
        System.out.println();
        while (n <= 0) {
            System.out.print("Please enter in the value of the size of the original set (n). \nMust be larger than 0: ");
            n = reader.nextInt();
            System.out.println();
        }
        return n;
    }

    /**
     * recursive method to calculate the permutations
     *
     * @param n - the original set of numbers
     * @param r - the length of the unique sequence
     * @return
     */
    private static int permut(int n, int r) { //passing in the numbers of r and n
        if (r == 1) {//base case
            return n;
        } else { //recursive case
            return n * permut(n - 1, r - 1);
        }


    }

}
