import java.rmi.RemoteException;

public class Product implements ProductInterface{ 
	// Attributes of product
	private String productcode;
	private String name;
	private String description;
	private int quantity;
	private double retailprice;
	private double storeprice;

	// Constructor for product
	public Product(String  newProductcode, String newName, String newDescription, int newQuantity, double newRetailprice, double newStoreprice) throws RemoteException{
		this.productcode = newProductcode;
		this.name = newName;
		this.description = newDescription;
		this.quantity = newQuantity;
		this.retailprice = newRetailprice;
		this.storeprice = newStoreprice;
	}

	// Method to view product details
	public String ViewProduct() throws RemoteException {
	    return "Productcode: " + this.productcode + "\n" +
	    		"Name: " + this.name + "\n" +
	           	"Description: " + this.description + "\n" +
	           	"Quantity: " + this.quantity + "\n" +
	           	"Retailprice: " + this.retailprice + "\n" +
	           	"Storeprice: " + this.storeprice;
}

	// Getters for product attributes
	public String getProductcode() throws RemoteException{ 
		return this.productcode;
	}
	public String getName() throws RemoteException{
		return this.name;
	}
	public String getDescription() throws RemoteException{
		return this.description;
	}
	public int getQuantity() throws RemoteException{
		return this.quantity;
	}
	public double getRetailprice() throws RemoteException{
		return this.retailprice;
	}
	public double getStoreprice() throws RemoteException{
		return this.storeprice;
	}

}
