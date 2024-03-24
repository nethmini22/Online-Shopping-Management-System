package Console;

import java.io.Serializable;

// Clothing class, a type of Product, represents clothing items in the shopping system
public class Clothing extends Product implements Serializable {

    // Attributes specific to Clothing class
    private String size;
    private String colour;

    // Default constructor
    public Clothing() {
    }

    // Parameterized constructor for creating Clothing objects with size and colour
    public Clothing(String size, String colour) {
        super(); // Call the superclass constructor (Product)
        this.size = size;
        this.colour = colour;
    }

    // Parameterized constructor for creating Clothing objects with all details
    public Clothing(String productID, String productName, int noOfAvailableItems, double price, String size, String colour) {
        super(productID, productName, noOfAvailableItems, price); // Call the superclass constructor (Product)
        this.size = size;
        this.colour = colour;
    }

    // Getter method for size
    public String getSize() {
        return size;
    }

    // Getter method for colour
    public String getColour() {
        return colour;
    }

    // Method to set details of the Clothing object
    public void setDetails(String productID, String productName, int noOfAvailableItems, double price, String size, String colour) {
        super.setProductID(productID);
        super.setProductName(productName);
        super.setNoOfAvailableItems(noOfAvailableItems);
        super.setPrice(price);
        this.size = size;
        this.colour = colour;
    }

    // Method to display detailed information about the Clothing object
    public String showProductDetails() {
        return "size - " + size +
                ", colour - " + colour + '\'';
    }

    // Override toString method to provide a formatted string representation of the Clothing object
    @Override
    public String toString() {
        return "Product ID : " + getProductID() + '\n' +
                "Name : " + getProductName() + '\n' +
                "Category : " + getCategory() + '\n' +
                "Size : " + size + '\n' +
                "Colour : " + colour + '\n' +
                "Items Available : " + getNoOfAvailableItems();
    }

    // Method to get the category of the Clothing object (always "Clothes")
    public String getCategory() {
        return "Clothes";
    }

    // Method to provide details for displaying in a shopping cart
    public String cartDetails() {
        return getProductID() + "\n" +
                getProductName() + "\n" +
                getSize() + ',' +
                getColour();
    }
}
