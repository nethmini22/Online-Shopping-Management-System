package Console;

// Interface representing the functionality of a shopping manager
public interface ShoppingManager {

    // Method to add a new product to the shopping system
    void addNewProduct();

    // Method to delete a product from the shopping system
    void deleteProduct();

    // Method to print the list of products in the shopping system
    void printProducts();

    // Method to save the current state of the shopping system in a file
    void saveInFile();

    // Method to reload the progress of the shopping system from a file
    void reloadProgress();
}
