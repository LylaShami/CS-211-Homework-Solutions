/**
 * Lyla Shami; 4-24-2022; CS 211; Shopping cart class: This class gives the program the ability to change the order based on
 * changing item amount, as well as giving total for order and added discount if needed.
 */

import java.util.ArrayList;


public class ShoppingCart {
    private boolean discount;
    ArrayList<ItemOrder> shoppingCartList;

    public ShoppingCart() {
        shoppingCartList = new ArrayList<>();

    }

    public void add(ItemOrder order) {
        shoppingCartList.add(order);

    }

    public void setDiscount(boolean value) {
        this.discount = value;
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < shoppingCartList.size(); i++) {
            total += shoppingCartList.get(i).getPrice();
        }
        return total;
    }


}
