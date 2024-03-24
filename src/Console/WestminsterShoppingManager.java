package Console;

import java.io.*;
import java.util.*;

// WestminsterShoppingManager class, implements ShoppingManager interface, manages products and user interactions
public class WestminsterShoppingManager implements ShoppingManager, Serializable {

    // ArrayLists to store products and customers
    public static ArrayList<Product> productList = new ArrayList<>();
    public static ArrayList<User> customerList = new ArrayList<>();

    // Method to add a new product to the system
    public void addNewProduct() {
        Scanner input = new Scanner(System.in);
        if (productList.size() < 50) {

            System.out.println("\n-------------ADD  PRODUCT----------------\n");

            String productID = validateStringInput("\tEnter the product ID : ", "Product ID");
            String productName = validateStringInput("\tEnter the product name : ", "Product name");
            int noOfProducts = validateIntegerInput("\tEnter the number of products available : ", "Number of products");
            double productPrice = validateDoubleInput("\tEnter the product price : ", "Product price");

            System.out.println();
            System.out.println("""
                    \tEnter 1 or 'C' to Add a Clothing Product
                    \tEnter 2 or 'E' to Add an Electronic Product
                    \n""");
            System.out.print("\tEnter your option : ");
            String typeInput = input.next();

            switch (typeInput) {
                case "1", "C":
                    Clothing clothingProduct = new Clothing();
                    System.out.print("\tEnter the size : ");
                    String size = input.next();
                    System.out.print("\tEnter the colour : ");
                    String colour = input.next();
                    clothingProduct.setDetails(productID, productName, noOfProducts, productPrice, size, colour);
                    productList.add(clothingProduct);
                    System.out.println("\n\tProduct added to the system !!!");
                    break;

                case "2", "E":
                    Electronics electronicProduct = new Electronics();
                    System.out.print("\tEnter the brand : ");
                    String brand = input.next();
                    int warrantyPeriod = validateIntegerInput("\tEnter the warranty period : ", "Warranty Period");
                    electronicProduct.setDetails(productID, productName, noOfProducts, productPrice, brand, warrantyPeriod);
                    productList.add(electronicProduct);
                    System.out.println("\n\tProduct added to the system !!!");
                    break;

                default:
                    System.out.println("\tInvalid Option !!!");
            }
        } else {
            System.out.println("Maximum limit of products (" + 50 + ") reached! Cannot add more products.\n");
        }
    }

    // Method to delete a product from the system
    @Override
    public void deleteProduct() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n-------------DELETE A PRODUCT--------------\n");

        System.out.println("-------Products currently added in the system------- ");
        for (Product product : productList) {
            System.out.println(product.getProductID() + " - " + product.getProductName());
        }

        System.out.print("\n\tEnter the product ID you want to delete : ");
        String deleteProductID = input.next();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductID().equals(deleteProductID)) {
                System.out.println("\tDetails of product to be deleted:" + productList.get(i).showProductDetails());
                productList.remove(i);
                System.out.println("\t Product Deleted");
                System.out.println("\tTotal Number of Products Available :" + productList.size());
            }
        }
    }

    // Method to print the list of products
    @Override
    public void printProducts() {
        sortProductListById();

        System.out.println("\n-------------Product List by Product ID-----------------");

        for (Product product : productList) {
            System.out.println(product);
        }
    }

    // Method to save product and customer information to a file
    @Override
    public void saveInFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/console/products.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(productList);
            objectOutputStream.writeObject(customerList);
            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Progress saved successfully!\n");

        } catch (IOException e) {
            System.out.println("An error occurred while saving progress!!! \n" + e);
        }
    }

    // Method to reload saved progress from a file
    public void reloadProgress() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/console/products.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            productList = (ArrayList<Product>) objectInputStream.readObject();
            customerList = (ArrayList<User>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reloading progress");
        }
    }

    // Private method to validate string input
    private String validateStringInput(String prompt, String fieldName) {
        Scanner input = new Scanner(System.in);
        String userInput;
        do {
            System.out.print(prompt);
            userInput = input.next();
            if (userInput.isEmpty()) {
                System.out.println("\tInvalid input. " + fieldName + " cannot be empty. Please try again.");
            }
        } while (userInput.isEmpty());
        return userInput;
    }

    // Private method to validate integer input
    private int validateIntegerInput(String prompt, String fieldName) {
        Scanner input = new Scanner(System.in);
        int userInput;
        do {
            System.out.print(prompt);
            while (!input.hasNextInt()) {
                System.out.print("\tInvalid input !!! Please enter a valid value : ");
                input.next(); // consume the invalid input
            }
            userInput = input.nextInt();
            if (userInput < 0) {
                System.out.println("\tInvalid input !!! No of items must be greater than or equal to zero. Please try again.");
            }
        } while (userInput < 0);
        return userInput;
    }

    // Private method to validate double input
    private double validateDoubleInput(String prompt, String fieldName) {
        Scanner input = new Scanner(System.in);
        double userInput;
        do {
            System.out.print(prompt);
            while (!input.hasNextDouble()) {
                System.out.print("\tInvalid input !!! Please enter a valid value : ");
                input.next(); // consume the invalid input
            }
            userInput = input.nextDouble();
            if (userInput <= 0) {
                System.out.println("\tInvalid input !!! Product price must be greater than zero. Please try again.");
            }
        } while (userInput <= 0);
        return userInput;
    }

    // Private method to sort the product list by product ID
    private void sortProductListById() {
        Comparator<Product> idComparator = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getProductID().compareTo(p2.getProductID());
            }
        };
        Collections.sort(productList, idComparator);
    }

    // Getter method for the product list
    public List<Product> getProductList() {
        return productList;
    }
}
