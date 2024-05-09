import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.stream.IntStream;

public class Server {
  public static void main(String[] args) {
    try {

      // Create Cart object
      Cart cart = new Cart();
      
      System.setProperty("java.rmi.server.hostname", "127.0.0.1");

      System.out.println("Server has been started...");
      System.out.println();
      System.out.println("---------------------------------------------");
      System.out.println("Viewing Products...");

      // Load XML file
      File xmlFile = new File("products.xml");

      // Get Document Builder
      DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder Builder = Factory.newDocumentBuilder();

      // Build Document
      Document document = Builder.parse(xmlFile);

      // Normalize the XML Structure
      document.getDocumentElement().normalize();

      // Get all products
      NodeList productList = document.getElementsByTagName("Product");
      
      // Create registry
      Registry registry = LocateRegistry.createRegistry(9100);

      // Process each product using Java's Stream API
      IntStream.range(0, productList.getLength())
        .mapToObj(i -> (Element) productList.item(i))
        .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
        .forEach(productElement -> {
          try {

            // Get product details in XML
            String productcode = productElement.getElementsByTagName("Productcode").item(0).getTextContent();
            String name = productElement.getElementsByTagName("Name").item(0).getTextContent();
            String description = productElement.getElementsByTagName("Description").item(0).getTextContent();
            int quantity = Integer.parseInt(productElement.getElementsByTagName("Quantity").item(0).getTextContent());
            double retailPrice = Double.parseDouble(productElement.getElementsByTagName("Retailprice").item(0).getTextContent());
            double storePrice = Double.parseDouble(productElement.getElementsByTagName("Storeprice").item(0).getTextContent());
        
            // Create Product object
            Product product = new Product(productcode, name, description, quantity, retailPrice, storePrice);
            
            // Export Product object
            ProductInterface stub_product = (ProductInterface) UnicastRemoteObject.exportObject(product, 0);
            
            // Bind Product object to registry
            registry.rebind(name, stub_product);

            System.out.println("---------------------------------------------");
            System.out.println(product.ViewProduct());
            System.out.println("---------------------------------------------");
          } catch (Exception e) {
            System.err.println("Error processing product: " + e);
          }
        });

      // Export Cart object
      CartInterface stub_cart = (CartInterface) UnicastRemoteObject.exportObject(cart, 0);
      registry.rebind("access_cart", stub_cart);

      System.out.println();
      System.out.println("Exporting and binding of Objects has been completed...");
      System.out.println("Accessing Cart..");
    } catch (Exception e) {
      System.err.println("Server exception: " + e);
    }
  }
}