import java.util.ArrayList;
import java.util.List;
//Lyla Noor Shami
// CS 211
// Craig Niiyama
//Class to implement the four method implementations done on LinkedIntLists.

public class Assignment9 {
    public static void main(String[] args) {
        //creating LinkedIntList object
        LinkedIntList tester = new LinkedIntList();
        //adding elements to it
        tester.add(1);
        tester.add(2);
        tester.add(3);
        tester.add(4);
        tester.add(5);
        tester.add(6);
        tester.add(7);
        tester.add(8);
        tester.add(9);
        tester.add(10);
        tester.add(11);
        tester.add(12);

        //test method for lastIndexOf
        System.out.println();
        testLastIndexOf(tester);

        //test method for CountDuplicates
        System.out.println();
        TestCountDuplicates(tester);

        //test method for HasTwoConsecutive
        System.out.println();
        testHasTwoConsecutive(tester);

        //test method for DeleteBack
        System.out.println();
        testDeleteBack(tester);

    }

    //testing the LastIndexOf
    private static void testLastIndexOf(LinkedIntList tester) {
        //print original
        System.out.println("Before lastIndexOf: " + tester.toString());
        //calling test
        System.out.println("Last index of 3 is: " + tester.lastIndexOf(3));
        System.out.println("After lastIndexOf:  " + tester.toString());
    }

    //testing the CountDuplicates
    private static void TestCountDuplicates(LinkedIntList tester) {
        //print original
        System.out.println("Before countDuplicates: " + tester.toString());
        //calling test
        System.out.println("Duplicates: " + tester.countDuplicates());
        System.out.println("After countDuplicates:  " + tester.toString());
    }

    //testing the hasTwoConsecutive
    private static void testHasTwoConsecutive(LinkedIntList tester) {
        //print original
        System.out.println("Before TwoConsecutive:  " + tester.toString());
        //calling test
        System.out.println("Are there consecutive elements: " + tester.hasTwoConsecutive());
        System.out.println("After TwoConsecutive: " + tester.toString());
    }

    //testing the DeleteBack
    private static void testDeleteBack(LinkedIntList tester) {
        //print original
        System.out.println("Before DeleteBack: " + tester.toString());
        //calling test
        System.out.println("Deleted value: " + tester.deleteBack());
        System.out.println("After DeleteBack: " + tester.toString());
    }

}
