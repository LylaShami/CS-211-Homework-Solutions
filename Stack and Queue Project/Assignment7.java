
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Name: Lyla Shami
// Class: CS 211
// Date:5-14-22
// Instructor: Craig Niiyama
// Description: Working with stack and queues.


public class Assignment7 {

    // the entry point of the class calling the test methods which in turn calling the assigned methods
    public static void main(String[] args) {
        testSeeingThreeMethod();
        testTwoStacksAreEqualMethod();
        testIsMirrored();

    }

    // pre : a stack of integers
    // post: every value in the stack is replaced with three occurrences of that value
    public static void seeingThree(Stack<Integer> s) {
        Stack<Integer> auxStack = new Stack<Integer>(); //creating a stack called auxStack
        // add all elements from the stack to the que
        while (!s.empty()) {//while the stack is not empty
            int temp = s.pop();//setting temporary variable that stack will pop out
            auxStack.push(temp); //add the last value that the stack pops out into the queue
        }
        //add each element to the stack three times
        //stack : []
        //que : [4,3,2,1,0]
        int origSize = auxStack.size(); // setting the original size of stack to variable called origSize
        for (int i = 0; i < origSize; i++) { //iterating through the stack
            int value = auxStack.pop(); // variable called value is equal to integer stack pops up
            s.push(value); //pushes value on to the top of the stack
            s.push(value);
            s.push(value);
            //end goal of the stack should be: [0,0,0,1,1,1,2,2,2,3,3,3,4,4,4]
        }
    }

// at this point the stack is empty and the queue is a loaded linked list
//TRY TO PRINT QUEUE


    // pre : two stacks of integers
    // post: a boolean result of true if the stacks are equal or false if the stacks are not equal
    public static boolean twoStacksAreEqual(Stack<Integer> s1, Stack<Integer> s2) {
        if (s1.equals(s2)) { //if stack s1 equals s2
            return true; // then return true
        }
        return false; // if not then return false
    }

    // pre : a queue of integers
    // post: returns true if the numbers in the queue represent a palindrome (and false otherwise).
    // A sequence of numbers is considered a palindrome if it is the same in reverse order
    public static boolean isMirrored(Queue<Integer> orgQ) {

        //used to check reverse order,
        // after it is loaded it will have the same elements in the reversed order
        Stack<Integer> s = new Stack<Integer>(); //creating stack of integers
        int orgSize = orgQ.size(); //setting variable for original size of q
        Queue<Integer> queueCopy = new LinkedList<>(orgQ); //making deep copy of orgQ

        //load the stack
        for (int i = 0; i < orgSize; i++) { //iterating through original size of orgQ
            s.push(orgQ.remove()); //the stack will push the value the original queue removes
        }

        //compare the stack [reversed order] to the que [normal order]
        while (!s.empty()) { //while  stack is not empty
            //whatever value stack pops does not equal queue copy
            if (s.pop() != queueCopy.remove()) {
                return false; //return false
            }
        }
        return true; // if not return true
    }

    // This is a test method testing twoStacksAreEqual method. It test both the true case and the false case
    private static void testIsMirrored() {

        Queue<Integer> myQueueP = new LinkedList<Integer>();
        ;

        for (int i = 0; i < 5; i++) {

            System.out.print(i);
            myQueueP.add(i);

        }
        for (int i = 3; i >= 0; i--) {

            System.out.print(i);
            myQueueP.add(i);

        }


        System.out.println();

        System.out.println(isMirrored(myQueueP) + " isMirrord");

    }

    //test method to test the testTwoStacksAreEqualMethod.
    //It tests cases of the same stack and not the same stack.
    private static void testTwoStacksAreEqualMethod() {
        Stack<Integer> myStack1 = new Stack<Integer>();
        Stack<Integer> myStack2 = new Stack<Integer>();
        Stack<Integer> myStack3 = new Stack<Integer>();
        Stack<Integer> myStack4 = new Stack<Integer>();

        for (int i = 0; i < 5; i++) {
            myStack1.push(i);
            myStack2.push(i);
            myStack4.push(i);

        }
        for (int i = 0; i < 6; i++) {
            myStack3.push(i);

        }

        System.out.println(twoStacksAreEqual(myStack1, myStack2) + " Same Stack ");

        System.out.println(twoStacksAreEqual(myStack3, myStack4) + " Not Same Stack");

    }

    //Method to test the SeeingThree method
    private static void testSeeingThreeMethod() {
        Stack<Integer> myStack = new Stack<Integer>();

        for (int i = 0; i < 5; i++) {
            myStack.push(i);

        }


        System.out.println();
        print(myStack);

        seeingThree(myStack);

        print(myStack);

    }

    // pre : a stack of integers
    // post: prints out the stack of integers
    private static void print(Stack<Integer> s) {
        Enumeration<Integer> e = s.elements();

        while (e.hasMoreElements()) System.out.print(e.nextElement() + " ");
        System.out.println();


    }

} //end of Assignment7