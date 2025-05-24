// This class extends Item and represents a toy
public class ToyItem extends Item {
    private String ageGroup;  // age recommendation for the toy

    // Constructor – includes recommended age group
    public ToyItem(String name, double price, int quantity, String ageGroup) {
        super(name, price, quantity);
        this.ageGroup = ageGroup;
    }

    // Display method including age group info
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Recommended Age Group: " + ageGroup);
    }
}
