// Supermarket.java – Interactive and immersive version with detailed comments
// Author: Caio – CCT College Dublin

import java.util.*;

public class Supermarket {
    // We use Scanner to receive input from the user via keyboard
    private static Scanner scanner = new Scanner(System.in);

    // This list stores all items the user adds to the cart during the session
    private static ArrayList<Item> cart = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\n🛒 Welcome to SupermarketItems Self-Checkout!\n");

        boolean running = true; // Controls the main loop
        while (running) {
            // Display the main menu options
            System.out.println("Choose what you'd like to do:");
            System.out.println("[A] Browse and add items");
            System.out.println("[V] View your cart");
            System.out.println("[R] Remove an item from your cart");
            System.out.println("[S] Sort your cart");
            System.out.println("[C] Checkout and pay");
            System.out.println("[X] Exit store");
            System.out.print("\n> Enter your action: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            // Match the user's choice to an action
            switch (choice) {
                case "A": browseAndAdd(); break;
                case "V": showCart(); break;
                case "R": removeItem(); break;
                case "S": sortCart(); break;
                case "C": checkout(); break;
                case "X":
                    System.out.println("\n👋 Thanks for shopping with us. Goodbye!");
                    running = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        }

        scanner.close(); // Close scanner at the end to free resources
    }

    // This method allows the user to select an item type and add it to the cart
    private static void browseAndAdd() {
        while (true) {
            System.out.println("\n📦 What type of item would you like to add?");
            System.out.println("[1] Food\n[2] Electronics\n[3] Clothing\n[4] Toys\n[B] Back to main menu");
            System.out.print("Choice: ");
            String type = scanner.nextLine().trim().toUpperCase();

            if (type.equals("B")) break; // Return to the main menu if user selects B

            // Ask common item details
            String name = ask("Enter name: ");
            double price = askDouble("Enter price: ");
            int qty = askInt("Enter quantity: ");

            // Based on the chosen type, ask for extra details and add the correct item
            switch (type) {
                case "1":
                    String exp = ask("Enter expiration date: ");
                    cart.add(new FoodItem(name, price, qty, exp));
                    break;
                case "2":
                    int warranty = askInt("Enter warranty (months): ");
                    cart.add(new ElectronicItem(name, price, qty, warranty));
                    break;
                case "3":
                    String size = ask("Enter size (e.g. M, L): ");
                    cart.add(new ClothingItem(name, price, qty, size));
                    break;
                case "4":
                    String ageGroup = ask("Enter recommended age group: ");
                    cart.add(new ToyItem(name, price, qty, ageGroup));
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }
    }

    // This method prints all items currently in the user's cart
    private static void showCart() {
        if (cart.isEmpty()) {
            System.out.println("\n🛒 Your cart is currently empty.\n");
            return;
        }

        System.out.println("\n🧾 Cart Items:");
        for (Item item : cart) {
            item.displayDetails(); // Show details for each item
            System.out.println("--------------------");
        }
        System.out.printf("Total items: %d\n\n", cart.size());
    }

    // This method allows the user to remove an item by name
    private static void removeItem() {
        System.out.print("\nEnter the name of the item to remove: ");
        String toRemove = scanner.nextLine().trim();
        // Search and remove by case-insensitive match
        boolean removed = cart.removeIf(item -> item.getName().equalsIgnoreCase(toRemove));

        if (removed) {
            System.out.println("✅ Item removed from your cart.\n");
        } else {
            System.out.println("❌ Item not found in your cart.\n");
        }
    }

    // This method allows sorting the cart items alphabetically or by price
    private static void sortCart() {
        System.out.println("\nHow would you like to sort your cart?");
        System.out.println("[1] By name (A-Z)\n[2] By price (low to high)\n[B] Back");
        System.out.print("Choice: ");
        String sortOption = scanner.nextLine().trim().toUpperCase();

        switch (sortOption) {
            case "1":
                cart.sort(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER));
                System.out.println("✅ Sorted by name.\n");
                break;
            case "2":
                cart.sort(Comparator.comparingDouble(Item::getPrice));
                System.out.println("✅ Sorted by price.\n");
                break;
            case "B": break;
            default:
                System.out.println("Invalid option. Try again.\n");
        }
    }

    // This method shows the receipt and asks how the user wants to pay
    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\n🛒 You can't checkout with an empty cart!\n");
            return;
        }

        double total = 0;
        System.out.println("\n🧾 Final Receipt:");
        for (Item item : cart) {
            item.displayDetails();
            total += item.getPrice() * item.getQuantity();
            System.out.println("--------------------");
        }
        System.out.printf("💳 Total amount due: €%.2f\n", total);

        // Ask how the customer wants to pay
        System.out.println("\nHow would you like to pay?");
        System.out.println("[1] Cash\n[2] Card");
        System.out.print("Payment method: ");
        String payment = scanner.nextLine().trim();

        // Handle the payment option
        if (payment.equals("1")) {
            System.out.println("\n🧾 Payment received in cash. Thank you for shopping!");
        } else if (payment.equals("2")) {
            System.out.println("\n🧾 Payment processed by card. Thank you for shopping!");
        } else {
            System.out.println("\n⚠️ Invalid payment method. Transaction cancelled.");
            return;
        }

        cart.clear(); // Clear the cart after successful payment
    }

    // Asks a question and returns the user's input as a string
    private static String ask(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    // Keeps asking until the user enters a valid integer
    private static int askInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Keeps asking until the user enters a valid decimal number
    private static double askDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}
