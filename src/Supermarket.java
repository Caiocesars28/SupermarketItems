import java.util.ArrayList;
import java.util.Scanner;

public class Supermarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> cart = new ArrayList<>();

        int option = -1;
        while (option != 0) {
            System.out.println("\n=== Supermarket Menu ===");
            System.out.println("1. Add Food Item");
            System.out.println("2. Add Electronic Item");
            System.out.println("3. Add Clothing Item");
            System.out.println("0. Proceed to Checkout");
            System.out.print("Enter your choice: ");

            try {
                option = Integer.parseInt(scanner.nextLine().trim());

                switch (option) {
                    case 1:
                        System.out.print("Enter name: ");
                        String foodName = scanner.nextLine().trim();

                        double foodPrice = askForPositiveDouble(scanner, "Enter price: ");
                        int foodQty = askForPositiveInt(scanner, "Enter quantity: ");

                        System.out.print("Enter expiration date: ");
                        String expiry = scanner.nextLine().trim();

                        cart.add(new FoodItem(foodName, foodPrice, foodQty, expiry));
                        break;

                    case 2:
                        System.out.print("Enter name: ");
                        String elecName = scanner.nextLine().trim();

                        double elecPrice = askForPositiveDouble(scanner, "Enter price: ");
                        int elecQty = askForPositiveInt(scanner, "Enter quantity: ");
                        int warranty = askForPositiveInt(scanner, "Enter warranty in months: ");

                        cart.add(new ElectronicItem(elecName, elecPrice, elecQty, warranty));
                        break;

                    case 3:
                        System.out.print("Enter name: ");
                        String clothName = scanner.nextLine().trim();

                        double clothPrice = askForPositiveDouble(scanner, "Enter price: ");
                        int clothQty = askForPositiveInt(scanner, "Enter quantity: ");

                        System.out.print("Enter size: ");
                        String size = scanner.nextLine().trim();

                        cart.add(new ClothingItem(clothName, clothPrice, clothQty, size));
                        break;

                    case 0:
                        System.out.println("\n=== Checkout ===");
                        double total = 0;

                        for (Item item : cart) {
                            item.displayDetails();
                            total += item.getPrice() * item.getQuantity();
                        }

                        System.out.printf("Total amount due: €%.2f\n", total);
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid input (numbers where expected).");
            }
        }

        scanner.close();
    }

    // Validates and asks again until a positive double is entered
    public static double askForPositiveDouble(Scanner scanner, String message) {
        double value = -1;
        while (value <= 0) {
            try {
                System.out.print(message);
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Value must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
        return value;
    }

    // Validates and asks again until a positive integer is entered
    public static int askForPositiveInt(Scanner scanner, String message) {
        int value = -1;
        while (value <= 0) {
            try {
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Value must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
        return value;
    }
}
