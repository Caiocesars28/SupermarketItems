// This class extends Item and represents a clothing item
public class ClothingItem extends Item {
    private String size;  // size of the clothing item (e.g. S, M, L)

    // Constructor – includes size
    public ClothingItem(String name, double price, int quantity, String size) {
        super(name, price, quantity);
        this.size = size;
    }

    // Display method including size info
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Size: " + size);
    }
}
