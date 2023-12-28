///Lyla Noor Shami
// CS 211
// Craig Niiyama
// This class is a HuffmanNode which is the building block of a Huffman tree. It contains the printSideways method
// which helps with outputting the tree, also has the compareTo method which is used while building the priority queue
//using the greedy algorithm.

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class HuffmanNode implements Comparable<HuffmanNode> {
    public HuffmanNode left;
    public HuffmanNode right;
    public int frequency;
    public char character;


    @Override
    public int compareTo(HuffmanNode other) { //passed in Huffman node called other
        //check the type
        if (!(other instanceof HuffmanNode)) { //if other is not a HuffmanNode
            throw new IllegalArgumentException("Can not compare against huffman node."); //throw exception
        }

        //check that there is a frequency
        if (other.frequency == 0) { //if other has no frequency
            throw new NoSuchElementException("This node does not have a frequency.");// throw exception
        }

        //compare frequencies
        if (this.frequency == other.frequency) { //if frequency is equal to other frequency
            return 0;
        } else if (this.frequency > other.frequency) { //if frequency is greater  than other frequency
            return 1;
        } else { //if other frequency is greater than frequency
            return -1;
        }

    }


    public boolean isLeaf() {
        if (this.left == null && this.right == null) { // if both children are null
            return true;// node is a leaf
        }
        return false; //else return false
    }


    //provide a count of the characters  in  an  input  file
    //  place  those  counts  into  a  Map,  with  character  as  the  unique  key  mapped  into  the  integer
    //  counts of that character
    public static Map<Character, Integer> getCounts(FileInputStream input) throws IOException {
        Map<Character, Integer> counts = new HashMap<>();

        int i = input.read(); // grab the first character
        while (!(i == -1)) { // while the file returns valid numbers
            //while (i >= -1) { // while the file returns valid numbers

            char currentCharacter = (char) i; //cast each character

            if (counts.containsKey(currentCharacter)) {// if the character already exists in the map
                int oldValue = counts.get(currentCharacter);//get the old value
                counts.put(currentCharacter, oldValue + 1); // restore the key with the new value
                i = input.read(); // advance to the next letter

            } else { // if the map did not contain the character
                counts.put(currentCharacter, 1); // store the char in the map with a count of 1
                i = input.read(); // advance to the next letter

            }
        }

        counts.put((char) 256, 1); // manually adding the eof as per the specification document
        return counts;
    }

    @Override
    public String toString() {
        return "{" + "left=" + left + ", right=" + right + "frequency=" + frequency + ", character=" + character + '}';
    }

    //getters and setters
    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}

