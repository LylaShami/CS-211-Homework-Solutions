import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
//Lyla Noor Shami
// CS 211
// Craig Niiyama
// Implementing three methods that work with ArrayIntLists.

// Class ArrayIntList can be used to store a list of integers.
public class ArrayIntList implements Iterable<Integer> {
    private int[] elementData;  // list of integers
    private int size = 0;       // current number of elements in the list

    public static final int DEFAULT_CAPACITY = 10;

    // post: constructs an empty list of default capacity
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    // pre : capacity >= 0
    // post: constructs an empty list with the given capacity
    private ArrayIntList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be at least 0: " + capacity);
        }
        elementData = new int[capacity];
    }

    // for creating test cases more easily
    public ArrayIntList(int... elements) {
        this(Math.max(DEFAULT_CAPACITY, elements.length * 2));
        for (int n : elements) {
            add(n);
        }
    }

    // for creating test cases more easily
    public ArrayIntList(int element, boolean notCapacity) {
        this();
        add(element);
    }

    // for creating test cases more easily
    public ArrayIntList(Collection<Integer> elements) {
        this(Math.max(DEFAULT_CAPACITY, elements.size() * 2));
        for (int n : elements) {
            add(n);
        }
    }

    // copy constructor; for creating test cases more easily
    public ArrayIntList(ArrayIntList list) {
        this(Math.max(DEFAULT_CAPACITY, list.size() * 2));
        addAll(list);
    }

    // pre : size() < capacity (elementData.length)
    // post: appends the given value to the end of the list
    public void add(int value) {
        add(size, value);
    }

    // pre: size() < capacity (elementData.length) && 0 <= index <= size()
    // post: inserts the given value at the given index, shifting subsequent
    //     values right
    public void add(int index, int value) {
        checkIndex(index, 0, size);
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // post: appends all values in the given list to the end of this list
    public void addAll(ArrayIntList other) {
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    // post: list is empty
    public void clear() {
        size = 0;
    }

    // post: returns true if the given value is contained in the list, false otherwise
    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    // post: ensures that the underlying array has the given capacity; if not,
    // the size is doubled (or more if given capacity is even larger)
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            int[] newList = new int[newCapacity];
            for (int i = 0; i < size; i++) {
                newList[i] = elementData[i];
            }
            elementData = newList;
        }
    }

    // returns true if o is an ArrayIntList with the same size and elements as this one
    public boolean equals(Object o) {
        if (!(o instanceof ArrayIntList)) {
            return false;
        }

        ArrayIntList other = (ArrayIntList) o;
        if (this.size != other.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (elementData[i] != other.elementData[i]) {
                return false;
            }
        }

        return true;
    }

    // pre : 0 <= index < size()
    // post: returns the integer at the given index in the list
    public int get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // post: returns capacity of this list's underlying array
    public int getCapacity() {
        return elementData.length;
    }

    // post : returns the position of the first occurence of the given
    //      value (-1 if not found)
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // post: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns an iterator for this list
    public Iterator<Integer> iterator() {
        return new ArrayIntListIterator(this);
    }

    // pre : 0 <= index < size()
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }

    // post: removes all occurrences of the values in the given list from this list
    public void removeAll(ArrayIntList other) {
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (!other.contains(elementData[i])) {
                elementData[newSize] = elementData[i];
                newSize++;
            }
            size = newSize;
        }
    }

    // pre : 0 <= index < size()
    // post: replaces the integer at the given index with the given value
    public void set(int index, int value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: returns the current number of elements in the list
    public int size() {
        return size;
    }

    // post: returns an array version of the contents of this list
    public int[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result += ", ";
            }
            if (i < elementData.length) {
                result += elementData[i];
            } else {
                // student's code is bogus; OOB
                result += "OOB!";
            }
        }
        result += "]";
        return result;
    }

    // helpers to make sure indexes do not fall out of bounds
    private void checkIndex(int index) {
        checkIndex(index, 0, size - 1);
    }

    private void checkIndex(int index, int min, int max) {
        if (!(min <= index && index <= max)) { //if index is not btwn 0 and size-1
            throw new ArrayIndexOutOfBoundsException("Illegal index " + index + "; must be between " + min + " and " + max + "\n" + "list : " + toString() + " (size=" + size + ")\n" + "array: " + Arrays.toString(elementData) + " (capacity=" + elementData.length + ")");
        }
    }


    // Stuart Reges
    // 4/4/05
    //
    // The ArrayIntListIterator class provides a set of utilities for iterating
    // over an ArrayIntList and possibly removing values from the list.

    private static class ArrayIntListIterator implements Iterator<Integer> {
        private ArrayIntList list;    // list to iterate over
        private int position;          // current position within the list
        private boolean removeOK;      // whether it's okay to remove now

        // pre : list != null
        // post: constructs an iterator for the given list
        public ArrayIntListIterator(ArrayIntList list) {
            this.list = list;
            position = 0;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        public boolean hasNext() {
            return position < list.size();
        }

        // pre : hasNext()
        // post: returns the next element in the iteration
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int result = list.get(position);
            position++;
            removeOK = true;
            return result;
        }

        // pre : next() has been called without a call on remove (i.e., at most one
        //     call per call on next)
        // post: removes the last element returned by the iterator
        public void remove() {
            if (!removeOK) throw new IllegalStateException();
            list.remove(position - 1);
            position--;
            removeOK = false;
        }
    }

    //Precondition: call: UpToNow() on a valid ArrayIntList
    //PostCondition: Creates a new ArrayIntList with each element as running total of original list given its indices
    public ArrayIntList upToNowTotal() {
        ArrayIntList addedList = new ArrayIntList(); //creating new arrayIntList
        int sum = 0; //initialing variable called sum
        if (this.size == 0) { //if list is empty
            return addedList; //return empty list
        }
        for (int i = 0; i < this.size; i++) { //iterating through the list
            sum += this.elementData[i];

            addedList.add(sum); // inserting sum to addedList
        }
        return addedList; // return the new arrayIntList
    }

    //Precondition: call: isPairSorted() on a valid ArrayIntList
    //PostCondition: returns boolean true if each pair in list is sorted. Ignoring last element if list is odd.
    public boolean isPairSorted() {
        int closeToLastElement = size - 1; //initializing variable to equal the element before last element

        for (int i = 0; i < size; i += 2) { //pairing elements together by incrementing by two
            if (i == closeToLastElement) { //if i's next element is last element
                break;//break out of for loop in case of odd number of elements
                // decide what to do, and how to stay in bounds [1,2,3,4,5]
            } else if (elementData[i] > elementData[i + 1]) { //if element at index i is more than the next element
                return false; //returning false because it's not in ascending order
            }
        }
        return true;//if pairs are in ascending order, return true
    }

    //Precondition: call: removeLast() on a valid ArrayList
    //PostCondition: removes the n number of elements off of the end of the arrayIntList.
    public void removeLast(int n) { //taking an integer n as a parameter

        if (n > this.size || n < 0) { //if n is bigger than the number, and n is smaller than zero
            throw new NoSuchElementException("Please recalculate with a number within the length of the list");// throw exception
        } else { // if there is no exception
            if (n == size) { // if the integer of n is equal to the size
                elementData = new int[DEFAULT_CAPACITY]; //set new with default capacity
                size = 0; // reset size
                return; // if this is the case then return

            }
            int orgSize = size; //created variable that holds original size
            for (int i = orgSize - 1; i >= orgSize - n; i--) { //i is starting at the end, i should be more than org size minus n, decrement i
                elementData[i] = elementData[i - 1]; //the element at i should be the same as the element behind it
                size--; //decrement the size
            }
        }

    }

    //Precondition:  call: fill() on a valid arraylist.
    //PostCondition: fills each element in arrayIntList with value that is passed in.
    public void fill(int value) { //passing in a variable integer named index
        for (int i = 0; i < size; i++) { // iterating through list
            elementData[i] = value; //setting i equal to any value passed in

        }

    }

}









