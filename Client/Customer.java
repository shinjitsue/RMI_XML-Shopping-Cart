import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Customer {
    public static void main(String[] args) {
        try {
            // Locate the registry.
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

            // Get the references of exported object from RMI Registry
            ProductInterface laptop = (ProductInterface) registry.lookup("MacBook Pro");
            ProductInterface mobilePhone = (ProductInterface) registry.lookup("iPhone 12");
            ProductInterface charger = (ProductInterface) registry.lookup("Apple Charger");
            ProductInterface powerBank = (ProductInterface) registry.lookup("Anker PowerCore");

            // Create an array of products
            ProductInterface[] products = {laptop, mobilePhone, charger, powerBank};

            // Get the reference of exported object from RMI Registry
            CartInterface cart = (CartInterface) registry.lookup("access_cart");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println();
                System.out.println(" ---------------");
                System.out.println("| Shopping Cart |");
                System.out.println(" ---------------");
                System.out.println("\n1. View Products");
                System.out.println("2. View Cart");
                System.out.println("3. Add Product to Cart");
                System.out.println("4. Remove All Products from Cart");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt(); 
                System.out.println();

                switch (choice) {
                    case 1:
                        for (ProductInterface product : products) {
                            System.out.println("---------------------------------------------");
                            System.out.println(product.ViewProduct());
                            System.out.println("---------------------------------------------");
                        }
                        break;
                    case 2:
                        System.out.println(cart.viewAddedProducts());
                        break;
                    case 3:
                        System.out.println("Choose a product to add to the cart: ");
                        for (int i = 0; i < products.length; i++) {
                            System.out.println((i + 1) + ". " + products[i].getName());
                        }
                        System.out.print("Enter your choice: ");
                        int productChoice = scanner.nextInt();
                        cart.addProduct(products[productChoice - 1]);
                        break;
                    case 4:
                        cart.removeAllProducts();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        } catch (Exception e) {
            System.out.println("Client side error..." + e);
        }
    }
}