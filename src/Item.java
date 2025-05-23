                            /*
 * Step 1 – Core class hierarchy for the Supermarket assignment
 * Author: Caio
 * Date: 23 May 2025
 * This file shows the Item superclass and three subclasses (FoodItem, ElectronicItem, ClothingItem).
 * Each class should be placed in its own .java file in your NetBeans project.
 * For simplicity no package statement is included, but you can add one if you organise your project in packages.
 */

///////////////////// Item.java /////////////////////
public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void displayDetails() {
        System.out.println("Item: " + name + ", Price: €" + price + ", Quantity: " + quantity);
    }
}

