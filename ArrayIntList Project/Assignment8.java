//Lyla Noor Shami
//CS 211
//Craig Niiyama
//Class to implement the three method implementations done on ArrayIntLists.
public class Assignment8 {

    public static void main(String[] args) {
        //creating an arrayIntList object
        ArrayIntList tester = new ArrayIntList();
        //adding elements to it
        tester.add(1);
        tester.add(10);
        tester.add(4);
        tester.add(3);
        tester.add(6);
        tester.add(7);
        tester.add(44);
        tester.add(33);
        tester.add(5);
        tester.add(9);
        //method to test upToNowTotal method
        testUpToNowTotal(tester);
        //method to test isPairSorted method
        testIsPairSorted(tester);
        // method to test RemoveLast method
        testRemoveLast(tester);
        //method to test Fill method
        testFill(tester);

    }

    //testing the upToNowMethod
    private static void testUpToNowTotal(ArrayIntList tester) {
        //print original
        System.out.println("Before upToNow: " + tester.toString());
        //call your test originalList.upToNow()
        System.out.println("After upToNow: " + tester.upToNowTotal());


    }

    //testing the testIsPairMethod
    private static void testIsPairSorted(ArrayIntList tester) {
        //print original
        System.out.println("Before isPairSorted: " + tester.toString());
        //call you test originalList.isPairSorted()
        System.out.println("After isPairSorted: " + tester.isPairSorted());


    }

    //test the removeLast method
    private static void testRemoveLast(ArrayIntList tester) {
        //print original
        System.out.println("Before testRemoveLast: " + tester.toString());
        //call you test originalList.removeLast()
        tester.removeLast(5); //input integer of n
        System.out.println("After testRemoveLast: " + tester.toString());

    }

    //testing the fill method
    public static void testFill(ArrayIntList tester) {
        //print original
        System.out.println("Before test fill: " + tester.toString());
        //call you test originalList.testFill()
        tester.fill(3); // input integer of value
        System.out.println("After test fill: " + tester.toString());
    }
}






