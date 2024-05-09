# Java RMI Shopping Cart with XML Parsing

This project is a simple shopping cart system implemented using Java's Remote Method Invocation (RMI) and XML parsing. It simulates a shopping experience where a customer can view products, add them to a cart, and view the cart. Additionally, an admin can view the product details.

## Features

- **Java RMI**: The project uses Java RMI to allow interaction between the client and server. The server exports `Product` and `Cart` objects, and the client can call methods on these objects remotely. This allows the client to add products to the cart and view the cart without directly manipulating the `Product` and `Cart` objects.

- **XML Parsing**: The server reads an XML file to get the list of available products. Each product is then exported as a remote object that the client can interact with. This allows the server to easily update the list of available products by modifying the XML file.

## How it Works

1. The server starts and reads the `products.xml` file to get the list of available products. Each product is created as a `Product` object and exported as a remote object.

2. The server also creates a `Cart` object and exports it as a remote object. The `Cart` object contains a list of `Product` objects that the customer has added to the cart.

3. The client starts and displays a menu to the user. The user can choose to view the available products, view the cart, add a product to the cart, or exit the program.

4. When the user chooses to view the products, the client calls the `ViewProduct()` method on each `Product` object to display the product details.

5. When the user chooses to add a product to the cart, the client calls the `addProduct()` method on the `Cart` object with the chosen `Product` object as the argument.

6. When the user chooses to view the cart, the client calls the `viewAddedProducts()` method on the `Cart` object to display the products in the cart.

7. The admin can also view the product details by calling the `ViewProduct()` method on each `Product` object.

## How to Run

1. Compile all the `.java` files.
2. Start the RMI registry.
3. Run the `Server.java` file to start the server.
4. Run the `Customer.java` file to start the client.
5. Run the `Admin.java` file to start the admin program.

## Files

- `ProductInterface.java`: This interface defines the methods that a `Product` object must implement.

- `CartInterface.java`: This interface defines the methods that a `Cart` object must implement.

- `Server.java`: This is the server program. It reads an XML file to get the list of products, exports the `Product` and `Cart` objects, and binds them to the RMI registry.

- `Product.java`: This class implements the `ProductInterface` and represents a product in the store. It has private attributes for the product code, name, description, quantity, retail price, and store price. It also has a `ViewProduct()` method that returns a string representation of the product details, and getters for the product attributes.

- `Cart.java`: This class implements the `CartInterface` and represents a shopping cart. It has a private list of `ProductInterface` objects, `addedProducts`, which represents the products added to the cart. It also has methods to add a product to the cart, remove all products from the cart, and view the products in the cart.

- `Customer.java`: This is the client program. It looks up the `Product` and `Cart` objects in the RMI registry and allows the user to interact with them.

- `Admin.java`: This is the admin program. It looks up the `Product` objects in the RMI registry and allows the admin to view the product details.
