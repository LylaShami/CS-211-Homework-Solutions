import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
//Lyla Noor Shami
// CS 211
// Craig Niiyama
// Implementing four methods that work with LinkedIntLists

// A LinkedIntList object can be used to store a list of integers.
public class LinkedIntList {
    private ListNode front;   // node holding first value in list (null if empty)
    private String name = "front";   // string to print for front of list

    // Constructs an empty list.
    public LinkedIntList() {
        front = null;
    }

    // Constructs a list containing the given elements.
    // For quick initialization via Practice-It test cases.
    public LinkedIntList(int... elements) {
        this("front", elements);
    }

    public LinkedIntList(String name, int... elements) {
        this.name = name;
        if (elements.length > 0) {
            front = new ListNode(elements[0]);
            ListNode current = front;
            for (int i = 1; i < elements.length; i++) {
                current.next = new ListNode(elements[i]);
                current = current.next;
            }
        }
    }

    // Constructs a list containing the given front node.
    // For quick initialization via Practice-It ListNode test cases.
    private LinkedIntList(String name, ListNode front) {
        this.name = name;
        this.front = front;
    }

    // Appends the given value to the end of the list.
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value, front);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }

    // Inserts the given value at the given index in the list.
    // Precondition: 0 <= index <= size
    public void add(int index, int value) {
        if (index == 0) {
            front = new ListNode(value, front);
        } else {
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new ListNode(value, current.next);
        }
    }

    public boolean equals(Object o) {
        if (o instanceof LinkedIntList) {
            LinkedIntList other = (LinkedIntList) o;
            return toString().equals(other.toString());   // hackish
        } else {
            return false;
        }
    }

    // Returns the integer at the given index in the list.
    // Precondition: 0 <= index < size
    public int get(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Removes the value at the given index from the list.
    // Precondition: 0 <= index < size
    public void remove(int index) {
        if (index == 0) {
            front = front.next;
        } else {
            ListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }

    // Returns the number of elements in the list.
    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Returns a text representation of the list, giving
    // indications as to the nodes and link structure of the list.
    // Detects student bugs where the student has inserted a cycle
    // into the list.
    public String toFormattedString() {
        ListNode.clearCycleData();

        String result = this.name;

        ListNode current = front;
        boolean cycle = false;
        while (current != null) {
            result += " -> [" + current.data + "]";
            if (current.cycle) {
                result += " (cycle!)";
                cycle = true;
                break;
            }
            current = current.__gotoNext();
        }

        if (!cycle) {
            result += " /";
        }

        return result;
    }

    // Returns a text representation of the list.
    public String toString() {
        return toFormattedString();
    }

    // Returns a shorter, more "java.util.LinkedList"-like text representation of the list.
    public String toStringShort() {
        ListNode.clearCycleData();

        String result = "[";

        ListNode current = front;
        boolean cycle = false;
        while (current != null) {
            if (result.length() > 1) {
                result += ", ";
            }
            result += current.data;
            if (current.cycle) {
                result += " (cycle!)";
                cycle = true;
                break;
            }
            current = current.__gotoNext();
        }

        if (!cycle) {
            result += "]";
        }

        return result;
    }


    // ListNode is a class for storing a single node of a linked list.  This
    // node class is for a list of integer values.
    // Most of the icky code is related to the task of figuring out
    // if the student has accidentally created a cycle by pointing a later part of the list back to an earlier part.

    public static class ListNode {
        private static final List<ListNode> ALL_NODES = new ArrayList<ListNode>();

        public static void clearCycleData() {
            for (ListNode node : ALL_NODES) {
                node.visited = false;
                node.cycle = false;
            }
        }

        public int data;          // data stored in this node
        public ListNode next;     // link to next node in the list
        public boolean visited;   // has this node been seen yet?
        public boolean cycle;     // is there a cycle at this node?

        // post: constructs a node with data 0 and null link
        public ListNode() {
            this(0, null);
        }

        // post: constructs a node with given data and null link
        public ListNode(int data) {
            this(data, null);
        }

        // post: constructs a node with given data and given link
        public ListNode(int data, ListNode next) {
            ALL_NODES.add(this);
            this.data = data;
            this.next = next;
            this.visited = false;
            this.cycle = false;
        }

        public ListNode __gotoNext() {
            return __gotoNext(true);
        }

        public ListNode __gotoNext(boolean checkForCycle) {
            if (checkForCycle) {
                visited = true;

                if (next != null) {
                    if (next.visited) {
                        // throw new IllegalStateException("cycle detected in list");
                        next.cycle = true;
                    }
                    next.visited = true;
                }
            }
            return next;
        }
    }

    //Precondition: call: lastIndexOf() on valid LinkedIntList
    //PostCondition: returns last occurrence of value passed, if value is not in list then returns -1.
    public int lastIndexOf(int target) { //method signature passing in integer named target
        ListNode current = front; //setting position of first node as current
        int lastIndex = -1;// creating integer variable as lastIndex
        int index = 0;// creating integer variable as index

        while (current != null) { //while the node is not null

            //check if data is equal to target
            if (current.data == target) {
                //store index as lastIndex
                lastIndex = index;
            }
            index++; // increment the index variable
            current = current.next; //progressing the current position
        }
        return lastIndex; //returning the integer lastIndex
    }

    //Precondition: call: countDuplicates() on valid LinkedIntList
    //PostCondition: if list is empty returns empty, otherwise returns the number of duplicates in list.
    public int countDuplicates() {
        if (front == null) return 0; //if list is empty return empty

        ListNode next = front.next; // setting position next to front as next
        ListNode current = front; // setting position of front as current
        int pairCount = 0; // integer variable called pairCount

        while (next != null) { //while node at position of next is not null
            if (current.data == next.data) { //if the value at position current  is the same as the value at position next
                pairCount++; //increment pairCount
            }
            current = current.next; // progress current position
            next = next.next; //progress next position
        }
        return pairCount; // return value pairCount
    }

    //Precondition: call: hasTwoConsecutive() on valid LinkedIntList
    //PostCondition: if list is empty returns false, otherwise returns boolean value if list has two adjacent numbers
    //that are two consecutive numbers
    public boolean hasTwoConsecutive() {
        if (front == null) return false; //if list is empty return false

        ListNode current = front;// setting position of front as current
        ListNode next = front.next;// setting position next to front as next

        while (next != null) { //while node at position of next is not null
            if (current.data + 1 == next.data) { //if value at current position plus one equals value at next position
                return true; //return true
            }
            current = current.next; // progress current position
            next = next.next;//progress next position

        }
        return false; //return false
    }


    //Precondition: call: deleteBack() on valid LinkedIntList
    //PostCondition: if list is empty throws exception, otherwise delete last node in list and returns deleted value.
    public int deleteBack() {
        if (front == null) throw new NoSuchElementException(); //if list is empty throw no such element exception
        int removeVal = front.data; //integer called remove val equal to value at front position
        if (front.next == null) { //if position next to front position is null
            removeVal = front.data; // remove value equal to value at front position
            front = null; //set front to null
            return removeVal; //return value removed
        }

        ListNode next = front.next; // setting position next to front as next
        ListNode current = front;// setting position of front as current

        while (next != null) { //while node at position of next is not null

            if (next.next == null) { //if position next to next is null
                removeVal = next.data; //remove value equal to value at next position
                current.next = null; //set current to null
                break; //break out of while loop
            }

            current = current.next; // progress current position
            next = next.next; //progress next position
        }
        return removeVal; //return remove value

    }
}

