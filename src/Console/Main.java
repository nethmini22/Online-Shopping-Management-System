package Console;

import GUI.LoginPage;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManagerCentre = new WestminsterShoppingManager();

        // Check if a saved progress file exists and reload if found
        File temp = new File("src/console/products.txt");
        if (temp.exists()) {
            shoppingManagerCentre.reloadProgress();
            System.out.println("\nATTENTION! Saved progress from a previous run has been found! The progress has been " +
                    "reloaded automatically.");
        }

        // Display the main menu
        displayMenu(shoppingManagerCentre);
    }

    // Method to display the main menu and handle user input
    public static void displayMenu(WestminsterShoppingManager shoppingManagerCentre) {
        Scanner input = new Scanner(System.in);

        while (true) {
            // Print the menu
            System.out.println();
            System.out.println("""
                  --------WESTMINSTER MANAGEMENT CENTRE-------
                 
                  -----------------Main Menu-----------------
            
                   \tEnter 1 or 'A' to Add a new product.
                   \tEnter 2 or 'D' to Delete a product.
                   \tEnter 3 or 'P' to Print the list of the product.
                   \tEnter 4 or 'S' to Save in a file.
                   \tEnter 5 or 'G' to Launch the GUI.
                   \tEnter 0 or 'X' to Exit.
                   """);

            // Take the input
            System.out.print("Enter your option : ");
            String userInput = input.next();

            // Handle user input based on the selected option
            switch (userInput.toUpperCase()) {
                case "1", "A" -> shoppingManagerCentre.addNewProduct();
                case "2", "D" -> shoppingManagerCentre.deleteProduct();
                case "3", "P" -> shoppingManagerCentre.printProducts();
                case "4", "S" -> shoppingManagerCentre.saveInFile();
                case "5", "G" -> {
                    // Launch GUI by creating an instance of LoginPage
                    new LoginPage(shoppingManagerCentre);
                    // Save progress after GUI usage
                    shoppingManagerCentre.saveInFile();
                }
                case "0", "X" -> {
                    System.out.println("Exiting the Program......");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Option !!!");
            }
        }
    }
}
