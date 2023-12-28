///Lyla Noor Shami
// CS 211
// Craig Niiyama

// This class uses the HuffmanNode to build a Huffman tree. This class contains a compress method and a decompress
//method which utilizes the Huffman tree to encode and decode a text file.

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HuffmanTree {

    public HuffmanNode root;
    private Map<Character, Integer> countsMap;
    private Map<Character, String> encodings;
    private ArrayList<HuffmanNode> huffArrayList;

    public HuffmanTree(Map<Character, Integer> countsMap) { // Constructor
        this.countsMap = countsMap;

        //create nodes from the map and add them into the priQ
        fillHuffArrayList();

        //then we use the priQ to make the huffTree
        makeHuffTree();

        //assign the first node in the priority to the root
        this.root = huffArrayList.get(0);

        //create a map of encodings
        encodings = new HashMap<>();
        fillEncodingMap();

    }

    //traverse through the huffman tree while appending the stringBuilder (0 for left, 1 for right)
    private void fillEncodingMap() {
        fillEncodingMap(root, "");
    }

    // recursive helper method
    private void fillEncodingMap(HuffmanNode root, String characterCode) {
        if (!root.isLeaf()) { //if root is not leaf
            fillEncodingMap(root.right, characterCode + 1);
            fillEncodingMap(root.left, characterCode + 0);
        }
        if (root.isLeaf()) {
            encodings.put(root.character, characterCode);
        }
    }

    //sets nodes as children of other nodes using the greedy method
    private void makeHuffTree() {
        while (huffArrayList.size() > 1) {
            //take first two nodes add values, create new node with new values, set org nodes as children and delete org
            HuffmanNode first = huffArrayList.get(0);
            HuffmanNode second = huffArrayList.get(1);

            HuffmanNode newNode = new HuffmanNode();
            newNode.frequency = (first.frequency + second.frequency);
            newNode.left = first;
            newNode.right = second;

            //removing the first two nodes
            huffArrayList.remove(0); // remove the first, now everything has moved up one element
            huffArrayList.remove(0);// removing the second

            huffArrayList.add(newNode);

            Collections.sort(huffArrayList);
        }
    }

    private void fillHuffArrayList() {
        this.huffArrayList = new ArrayList<>(); //setting huffArrayList equal to new ArrayList
        for (char currentChar : this.countsMap.keySet()) { //for each current character inside counts key set
            huffArrayList.add(createHuffmanNode(currentChar)); //add node to priority queue
        }
        Collections.sort(huffArrayList);
    }

    private HuffmanNode createHuffmanNode(char currentChar) {
        //grabbing value associated with current character, setting value to freq
        int freq = this.countsMap.get(currentChar);
        HuffmanNode nodeFromMap = new HuffmanNode(); //made new HuffManNode called nodeFromMap
        nodeFromMap.character = currentChar; //set character in nodeFromMap to currentChar
        nodeFromMap.frequency = freq;//set nodeFromMap frequency to freq
        return nodeFromMap;
    }

    public StringBuilder decompress(StringBuilder inputString) { //inputString 1’s & 0’s
        return decompress(inputString, root);
    }

    private StringBuilder decompress(StringBuilder inputString, HuffmanNode currentNode) {
        StringBuilder decompressedString = new StringBuilder();
        char currentDigit = inputString.charAt(0);

        while (!isEndOfFile(currentNode)) { // while we have not hit the end of the file

            //grab and delete the first character
            currentDigit = inputString.charAt(0);
            inputString.deleteCharAt(0);

            //traverse the tree
            if (currentDigit == '0') { // go left
                currentNode = currentNode.left;
            } else {//go right
                currentNode = currentNode.right;
            }

            //if leaf then append
            if (currentNode.isLeaf()) {
                if(isEndOfFile(currentNode)){ // check if the current character is the end of file
                    break;
                }
                decompressedString.append(currentNode.character);
                currentNode = root;
            }
        }
        return decompressedString;
    }

    private boolean isEndOfFile(HuffmanNode currentNode) {
        if(currentNode.character == (char) -1) return true;
        if(currentNode.character == 'Ā') return true;
        if(currentNode.character =='\uFFFF') return true;
        return false;
    }


    public StringBuilder compress(InputStream inputFile) throws IOException { // inputFile is a text file

        //create a string builder object
        StringBuilder compressedSB = new StringBuilder();

        int currentByte = inputFile.read();//grab first letter

        while (currentByte != -1) {//traverse through the input stream
            //while (currentByte >= -1) {//traverse through the input stream
            char currentCharacter = (char) currentByte; //
            if (encodings.containsKey(currentCharacter)) {
                compressedSB.append(encodings.get(currentCharacter));
            }

            currentByte = inputFile.read(); //advance one character at a time}
        }
        compressedSB.append(encodings.get('Ā')); //manually add EOF
        return compressedSB;
    }

    public String printSideways() { // as per the method presented in Chapter 17.
        StringBuilder treeToString = new StringBuilder(); //made new StringBuilder called treeToString
        //recalling method
        return printSideways(treeToString, this.root, 0).toString(); //returning treeToString at root and level 0
    }

    private StringBuilder printSideways(StringBuilder treeToString, HuffmanNode root, int level) {
        if (root != null) {
            printSideways(treeToString, root.right, level + 1);// continue to the right side

            //inserting whitespace
            for (int i = 0; i < level; i++) {
                treeToString.append("        ");
            }

            treeToString.append(root.frequency);//appending the node's frequency
            if (root.character == 0) { //nodes without characters only need count
                treeToString.append("=count");
            } else {//nodes with characters
                treeToString.append("=char(" + (int) root.character + ")");
            }

            treeToString.append("\n\n");

            printSideways(treeToString, root.left, level + 1);//continuing on the left side
        }
        return treeToString;
    } //helper method

}