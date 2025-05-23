import java.util.Scanner;

public class Supermarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option = -1;
        while (option != 0) {
            System.out.println("=== Supermarket Menu ===");
            System.out.println("1. Add Food Item");
            System.out.println("2. Add Electronic Item");
            System.out.println("3. Add Clothing Item");
            System.out.println("0. Proceed to Checkout");
            System.out.print("Enter your choice: ");

            option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    System.out.println("You chose Food Item.");
                    break;
                case 2:
                    System.out.println("You chose Electronic Item.");
                    break;
                case 3:
                    System.out.println("You chose Clothing Item.");
                    break;
                case 0:
                    System.out.println("Proceeding to checkout...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
