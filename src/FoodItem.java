// This class extends Item and represents a food product
public class FoodItem extends Item {
    String expirationDate;  // expiration date of the food item

    // Constructor – adds expirationDate in addition to base Item info
    public FoodItem(String name, double price, int quantity, String expirationDate) {
        super(name, price, quantity); // call parent constructor
        this.expirationDate = expirationDate;
    }

    // Method to display details including the expiration date
    @Override
    public void displayDetails() {
        super.displayDetails(); // show base item details
        System.out.println("Expiration Date: " + expirationDate);
    }
}
