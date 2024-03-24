package GUI;

import Console.User;
import Console.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Console.WestminsterShoppingManager.customerList;
import static Console.WestminsterShoppingManager.productList;

// Login page for user authentication
public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;

    // Constructor to set up the login page
    public LoginPage(WestminsterShoppingManager shoppingManager) {
        // Set up components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");

        // Set up layout
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);
        mainPanel.add(exitButton);

        // Set up event handlers
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin(shoppingManager);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performRegister();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set up the frame
        this.setTitle("User Login");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(300, 200);
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    // Method to perform user login
    private void performLogin(WestminsterShoppingManager shoppingManager) {
        String enteredUsername = usernameField.getText();
        char[] enteredPasswordChars = passwordField.getPassword();
        String enteredPassword = new String(enteredPasswordChars);

        // Check if the entered username and password match any customer in the customerList
        boolean loginSuccessful = false;
        for (User customer : customerList) {
            if (enteredUsername.equals(customer.getUsername()) && enteredPassword.equals(customer.getPassword())) {
                // Login successful
                loginSuccessful = true;
                break;
            }
        }

        // Display appropriate message based on login success
        if (loginSuccessful) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Open the main GUI window
            openMainWindow(shoppingManager);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear password field for security
        passwordField.setText("");
    }

    // Method to open the main shopping window
    private void openMainWindow(WestminsterShoppingManager shoppingManager) {
        // Create an instance of the gui class
        gui mainWindow = new gui(shoppingManager, productList);

        // Dispose of the login window
        this.dispose();
    }

    // Method to perform user registration
    private void performRegister() {
        String enteredUsername = usernameField.getText();
        char[] enteredPasswordChars = passwordField.getPassword();
        String enteredPassword = new String(enteredPasswordChars);

        // Create a new User object with the entered username and password
        User newUser = new User();
        newUser.setDetails(enteredUsername, enteredPassword);
        customerList.add(newUser);

        // Display a message indicating successful registration
        JOptionPane.showMessageDialog(this, "Registration successful for user: " + enteredUsername, "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear fields after registration
        usernameField.setText("");
        passwordField.setText("");
    }

    // Main method to run the login page
    public static void main(String[] args, WestminsterShoppingManager shoppingManager) {
        SwingUtilities.invokeLater(() -> new LoginPage(shoppingManager));
    }
}
