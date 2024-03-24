package Console;

import java.io.Serializable;

// Abstract class representing a general product in the shopping system
public abstract class Product implements Serializable {

    // Attributes common to all products
    private String productID;            // Unique identifier for the product
    private String productName;          // Name of the product
    private int noOfAvailableItems;      // Number of available items in stock
    private double price;                // Price of the product

    // Default constructor
    public Product() {
    }

    // Parameterized constructor for creating Product objects with specific details
    public Product(String productID, String productName, int noOfAvailableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.noOfAvailableItems = noOfAvailableItems;
        this.price = price;
    }

    // Getter method for product ID
    public String getProductID() {
        return productID;
    }

    // Setter method for product ID
    public void setProductID(String productID) {
        this.productID = productID;
    }

    // Getter method for the number of available items
    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    // Setter method for the number of available items
    public void setNoOfAvailableItems(int noOfAvailableItems) {
        this.noOfAvailableItems = noOfAvailableItems;
    }

    // Getter method for the price
    public double getPrice() {
        return price;
    }

    // Setter method for the price
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter method for product name
    public String getProductName() {
        return productName;
    }

    // Setter method for product name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Abstract method to show details of a product (implementation in subclasses)
    public abstract String showProductDetails();

    // Abstract method to provide details for displaying in a shopping cart (implementation in subclasses)
    public abstract String cartDetails();

    // Override toString method to provide a formatted string representation of the Product object
    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", noOfAvailableItems=" + noOfAvailableItems +
                ", price=" + price +
                '}';
    }

    // Abstract method to get the category of the product (implementation in subclasses)
    public abstract String getCategory();
}
