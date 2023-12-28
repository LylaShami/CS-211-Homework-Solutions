/**
 * Lyla Shami; 4-24-2022; CS 211; Catalog Class:This class should be able to return the number of items in the list,
 * and give the program the ability to add items to the list if they so choose.
 *
 */

import java.util.ArrayList;

public class Catalog {
    ArrayList<Item> itemsList = new ArrayList<>();


    private String name;


    public Catalog(String name) {
        this.name = name;
    }

    public void add(Item item) {
        itemsList.add(item);
    }

    public int size() {
        return itemsList.size();
    }

    public Item get(int index) {
        return itemsList.get(index);
    }

    public String getName() {
        return name;

    }

}
