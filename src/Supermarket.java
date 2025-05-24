// Supermarket.java – Interactive and immersive version with detailed comments
// Writer: Caio Cesar – CCT College Dublin
// Student Number: 2024285
// Developed individually as part of the Object-Oriented Programming modul

import java.util.*;

public class Supermarket {
    // Scanner to read input from user throughout the program
    private static Scanner scanner = new Scanner(System.in);
     // Dynamic list that stores all items added to the cart
    private static ArrayList<Item> cart = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\n🛒 Welcome to SupermarketItems Self-Checkout!\n");
     //Greet the user at the beginning 
        boolean running = true; // Flag to control the menu loop
        while (running) {
            // Calculate and display the total amount in the cart before showing the menu
            double menuTotal = 0;
            for (Item item : cart) menuTotal += item.getPrice() * item.getQuantity();
            System.out.printf("🛒 (Cart Total: €%.2f)\n", menuTotal);
            // Show main options for the user
            System.out.println("Choose what you'd like to do:");
            System.out.println("[A] Browse and add items"); // Option to add items
            System.out.println("[V] View your cart"); // Option to view current items in cart
            System.out.println("[R] Remove an item from your cart"); // Option to remove specific item
            System.out.println("[S] Sort your cart"); // Option to sort cart alphabetically or by price
            System.out.println("[C] Checkout and pay");  // Option to finalize and pay
            System.out.println("[X] Exit store"); // Option to exit program
            System.out.print("\n> Enter your action: ");
            // Read user input, remove extra spaces and convert to uppercase for consistency
            String choice = scanner.nextLine().trim().toUpperCase();
           // Choose what to do based on user input
            switch (choice) {
                case "A": browseAndAdd(); break; // Opens item adding flow
                case "V": showCart(); break; // Shows list of items in car
                case "R": removeItem(); break; // Opens item removal process
                case "S": sortCart(); break; // Opens sorting options
                case "C": checkout(); break; // Starts checkout process
                case "X":
                    System.out.println("\n👋 Thanks for shopping with us. Goodbye!");
                    running = false; // Exit loop to stop the program
                    break;
                default:
                    System.out.println("Invalid choice. Try again.\n"); // Handles invalid input
            }
        }
        // Close scanner to avoid resource leak
        scanner.close(); 
    }
    // Allows the user to choose the item category and provide its details to add to cart
    private static void browseAndAdd() {
        while (true) {
            System.out.println("\n📦 What type of item would you like to add?");
            System.out.println("[1] Food\n[2] Electronics\n[3] Clothing\n[4] Toys\n[B] Back to main menu");
            System.out.print("Choice: ");
            String type = scanner.nextLine().trim().toUpperCase();

            if (type.equals("B")) break; // Exit from item-adding mode and go back to main menu
            
            String name;
            do {
                name = ask("Enter name: ");
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty.");
                }
            } while (name.isEmpty());
            // Ask and validate item price
            double price;
            do {
                price = askDouble("Enter price: ");
                if (price <= 0) {
                    System.out.println("Price must be greater than zero.");
                }
            } while (price <= 0); // Ask quantity directly (int only)

            int qty = askInt("Enter quantity: ");
            // Depending on item type, request extra data and create specific subclass
            switch (type) {
                case "1":
                    String exp = ask("Enter expiration date: "); // Additional info for food
                    cart.add(new FoodItem(name, price, qty, exp));
                    break;
                case "2":
                    int warranty = askInt("Enter warranty (months): "); // Additional info for electronics
                    cart.add(new ElectronicItem(name, price, qty, warranty));
                    break;
                case "3":
                    String size = ask("Enter size (e.g. M, L): "); // Additional info for clothing
                    cart.add(new ClothingItem(name, price, qty, size));
                    break;
                case "4":
                    String ageGroup = ask("Enter recommended age group: "); // Additional info for toys
                    cart.add(new ToyItem(name, price, qty, ageGroup));
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }
    }
    // Shows all items currently in the cart with their details and subtotal
    private static void showCart() {
        if (cart.isEmpty()) {
            System.out.println("🛒 Your cart is currently empty.");
            return;
        }
        
        System.out.println("Cart Items:");
        double total = 0;
        for (Item item : cart) {
            item.displayDetails(); // Call the item’s specific display method
            double subtotal = item.getPrice() * item.getQuantity();
            System.out.printf("Subtotal: €%.2f\n", subtotal);
            total += subtotal;
            System.out.println("--------------------");
        }
        System.out.printf("Total items: %d | Total: €%.2f\n\n", cart.size(), total);
    }
    // Allows the user to remove an item from the cart by name
    private static void removeItem() {
        System.out.print("Enter the name of the item to remove: ");
        String toRemove = scanner.nextLine().trim();

        for (Item item : cart) {
            if (item.getName().equalsIgnoreCase(toRemove)) {
                System.out.print("Are you sure you want to remove '" + item.getName() + "'? [Y/N]: ");
                String confirm = scanner.nextLine().trim().toUpperCase();
                if (confirm.equals("Y")) {
                    cart.remove(item); // Removes item from list
                    System.out.println("✅ Item removed from your cart.");
                } else {
                    System.out.println("❎ Removal cancelled.");
                }
                return;
            }
        }

        System.out.println("❌ Item not found in your cart."); // If item was not found
    }
    // Allows sorting the cart by name or price
    private static void sortCart() {
        System.out.println("How would you like to sort your cart?");
        System.out.println("[1] By name (A-Z)\n[2] By price (low to high)\n[B] Back");
        System.out.print("Choice: ");
        String sortOption = scanner.nextLine().trim().toUpperCase();

        switch (sortOption) {
            case "1":
                cart.sort(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER)); // Sorts by name alphabetically
                System.out.println("✅ Sorted by name.");
                break;
            case "2":
                cart.sort(Comparator.comparingDouble(Item::getPrice)); // Sorts by price ascending
                System.out.println("✅ Sorted by price.");
                break;
            case "B":
                break; // Returns to previous menu
            default:
                System.out.println("Invalid option. Try again.");
        }
    }
    // Displays the final receipt and asks for payment
    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("🛒 You can't checkout with an empty cart!");
            return;
        }

        double total = 0;
        System.out.println("🧾 Final Receipt:");
        for (Item item : cart) {
            item.displayDetails();
            double subtotal = item.getPrice() * item.getQuantity();
            System.out.printf("Subtotal: €%.2f\n", subtotal);
            total += subtotal;
            System.out.println("--------------------");
        }
        System.out.printf("💳 Total amount due: €%.2f\n", total);
        // Prompt for payment method
        System.out.println("How would you like to pay?");
        System.out.println("[1] Cash\n[2] Card");
        System.out.print("Payment method: ");
        String payment = scanner.nextLine().trim();

        if (payment.equals("1")) {
            System.out.println("🧾 Payment received in cash. Thank you for shopping!");
        } else if (payment.equals("2")) {
            System.out.println("🧾 Payment processed by card. Thank you for shopping!");
        } else {
            System.out.println("⚠️ Invalid payment method. Transaction cancelled.");
            return;
        }
        // Ask user to confirm finalization of purchase
        System.out.print("Are you sure you want to finalize the purchase and clear your cart? [Y/N]: ");
        String confirm = scanner.nextLine().trim().toUpperCase();
        if (confirm.equals("Y")) {
            cart.clear(); // Empties the cart
            System.out.println("✅ Purchase completed and cart cleared.");
        } else {
            System.out.println("❎ Checkout cancelled. Your cart remains unchanged.");
        }
    }
    // Method to ask a generic string input
    private static String ask(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }
    // Method to ask a validated integer input (for quantities, warranty, etc.)
    private static int askInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number."); // Handles non-numeric input
            }
        }
    }
    // Method to ask a validated decimal input (for prices)
    private static double askDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount."); // Handles non-numeric input
            }
        }
    }
}
