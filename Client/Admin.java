import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Admin{
	public static void main(String[] args){
		try{
			// Get the references of exported object from RMI Registry...

			//locate the registry.
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

            // Get the references of exported object from RMI Registry
            ProductInterface laptop = (ProductInterface) registry.lookup("MacBook Pro");
            ProductInterface mobilePhone = (ProductInterface) registry.lookup("iPhone 12");
            ProductInterface charger = (ProductInterface) registry.lookup("Apple Charger");
            ProductInterface powerBank = (ProductInterface) registry.lookup("Anker PowerCore");

            System.out.println(" -----------------------");
            System.out.println("| Admin - View Products |");
            System.out.println(" -----------------------");

            // Display product details
            System.out.println();
            System.out.println(laptop.ViewProduct()+ "\n");
            System.out.println(mobilePhone.ViewProduct()+ "\n");
            System.out.println(charger.ViewProduct()+ "\n");
            System.out.println(powerBank.ViewProduct()+ "\n");


		}catch(Exception e){
			System.out.println("Client side error..." + e);
		}
	}
} 