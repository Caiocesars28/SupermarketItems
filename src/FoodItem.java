public class FoodItem extends Item {
    private String expirationDate;

    public FoodItem(String name, double price, int quantity, String expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Expiration Date: " + expirationDate);
    }
}

