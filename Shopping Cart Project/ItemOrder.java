/**
 * Lyla Shami; 4-24-2022; CS 211; Item Order Class:The item order class takes the item and the amount you would wish
 * to buy and multiplies it together to give you a price when bought all together.
 *
 */
public class ItemOrder {
    private Item item;
    private int quantity;

    public ItemOrder(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;

    }

    public double getPrice() {
        return this.item.getPrice() * this.quantity;
    }

    public Item getItem() {
        return item;
    }
}
