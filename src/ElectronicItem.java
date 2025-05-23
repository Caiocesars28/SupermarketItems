public class ElectronicItem extends Item {
    private int warrantyMonths;

    public ElectronicItem(String name, double price, int quantity, int warrantyMonths) {
        super(name, price, quantity);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Warranty: " + warrantyMonths + " months");
    }
}

