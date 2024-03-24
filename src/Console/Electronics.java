package Console;

import java.io.Serializable;

// Electronics class, a type of Product, represents electronic items in the shopping system
public class Electronics extends Product implements Serializable {

    // Attributes specific to Electronics class
    private String brand;
    private int warrantyPeriod;

    // Default constructor
    public Electronics() {
    }

    // Parameterized constructor for creating Electronics objects with brand and warranty period
    public Electronics(String productID, String productName, int noOfAvailableItems, double price, String brand, int warrantyPeriod) {
        super(productID, productName, noOfAvailableItems, price); // Call the superclass constructor (Product)
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getter method for brand
    public String getBrand() {
        return brand;
    }

    // Getter method for warranty period
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    // Method to set details of the Electronics object
    public void setDetails(String productID, String productName, int noOfAvailableItems, double price, String brand, int warrantyPeriod) {
        super.setProductID(productID);
        super.setProductName(productName);
        super.setNoOfAvailableItems(noOfAvailableItems);
        super.setPrice(price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Method to display detailed information about the Electronics object
    public String showProductDetails() {
        return "brand - " + brand +
                ", Warranty period - " + warrantyPeriod + '\'';
    }

    // Override toString method to provide a formatted string representation of the Electronics object
    @Override
    public String toString() {
        return "Product ID : " + getProductID() + '\n' +
                "Name : " + getProductName() + '\n' +
                "Category : " + getCategory() + '\n' +
                "Brand : " + brand + '\n' +
                "Warranty Period : " + warrantyPeriod + '\n' +
                "Items Available : " + getNoOfAvailableItems();
    }

    // Method to get the category of the Electronics object (always "Electronics")
    public String getCategory() {
        return "Electronics";
    }

    // Method to provide details for displaying in a shopping cart
    public String cartDetails() {
        return getProductID() + '\n' +
                getProductName() + '\n' +
                getBrand() + ',' +
                getWarrantyPeriod();
    }
}
