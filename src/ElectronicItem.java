// This class extends Item and represents an electronic product
public class ElectronicItem extends Item {
    private int warrantyMonths;  // number of months of warranty

    // Constructor – includes warranty in months
    public ElectronicItem(String name, double price, int quantity, int warrantyMonths) {
        super(name, price, quantity);
        this.warrantyMonths = warrantyMonths;
    }

    // Display method including warranty info
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Warranty: " + warrantyMonths + " months");
    }
}
