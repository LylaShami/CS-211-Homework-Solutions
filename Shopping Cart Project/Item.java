/**Lyla Shami; 4-24-2022; CS 211; Item Class: This class formats the items name with the price accordingly, as well
 * as removing the ability to input a negative quantity for the number of items you would wish to purchase.
 *
 *
 */

import java.text.NumberFormat;

public class Item {

    private String name;
    private double price;
    private int bulkQuantity;
    private double bulkPrice;

    public Item(String name, double price) {
        this.name = name;
        if (0 > price) { //if price is negative
            throw new IllegalArgumentException();
        } else {
            this.price = price;
        }

    }

    public Item(String name, double price, int bulkQuantity, double bulkPrice) {
        this.name = name;

        if (0 > price || 0 > bulkQuantity || 0 > bulkPrice) {
            throw new IllegalArgumentException();

        } else {
            this.price = price;
            this.bulkPrice = bulkPrice;
            this.bulkQuantity = bulkQuantity;
        }
    }

    public double priceFor(double quantity) {
        if (0 > quantity) {
            throw new IllegalArgumentException();
        } else if (quantity < bulkQuantity) {
            return quantity * this.price;
        } else {
            return quantity * bulkPrice;
        }

    }

    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String priceString = nf.format(price);
        String bulkPriceString = nf.format(bulkPrice);
        String returnValue = name + ", " + priceString;
        if (bulkPrice > 0) {
            returnValue = returnValue + " (" + bulkQuantity + " for " + bulkPriceString + ")";
        }
        return returnValue;
    }

    public double getPrice() {
        return price;
    }
}