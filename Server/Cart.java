import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Cart implements CartInterface {
    private List<ProductInterface> addedProducts;

    // Constructor for cart
    public Cart() {
        this.addedProducts = new ArrayList<>();
    }

    // Method to add product to cart
    public void addProduct(ProductInterface product) throws RemoteException {
        addedProducts.add(product);
        System.out.println("\nProduct added to cart!");
    }

    // Method to remove all products from cart
    public void removeAllProducts() throws RemoteException {
        addedProducts.clear();
        System.out.println("\nAll products removed from cart!");
    }

    // Method to view added products
    public String viewAddedProducts() throws RemoteException {
        if (addedProducts.isEmpty()) {
            return "Cart is empty";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(" ------------------\n");
            sb.append("| Products in cart | \n");
            sb.append(" ------------------\n");
            sb.append("----------------------------------------\n");
            for (ProductInterface product : addedProducts) {
                sb.append("Product Code: ").append(product.getProductcode()).append("\n");
                sb.append("Name: ").append(product.getName()).append("\n");
                sb.append("Description: ").append(product.getDescription()).append("\n");
                sb.append("Quantity: ").append(product.getQuantity()).append("\n");
                sb.append("Retail Price: ").append(product.getRetailprice()).append("\n");
                sb.append("Store Price: ").append(product.getStoreprice()).append("\n\n");
            }
            sb.append("----------------------------------------");
            return sb.toString();
        }
    }
}