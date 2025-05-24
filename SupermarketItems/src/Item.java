// This class is the base for all items in the supermarket
public class Item {
    private String name;       // name of the item
    private double price;      // price of one unit
    private int quantity;      // number of units

    // Constructor method – sets name, price and quantity
    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter for name
    public String getName() {
        return this.name;
    }

    // Getter for price
    public double getPrice() {
        return this.price;
    }

    // Getter for quantity
    public int getQuantity() {
        return this.quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to display item details (name, price, quantity)
    public void displayDetails() {
        System.out.println("Item: " + name + ", Price: €" + price + ", Quantity: " + quantity);
    }
}
