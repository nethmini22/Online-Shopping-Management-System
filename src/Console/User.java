package Console;

// Class representing a user in the shopping system
public class User {
    private String username;
    private String password;

    // User's shopping cart
    private ShoppingCart cart;

    // Flag indicating if the user is eligible for a first-time offer
    boolean firstTimeOffer = true;

    // Getter method for the firstTimeOffer flag
    public boolean isFirstTimeOffer() {
        return firstTimeOffer;
    }

    // Setter method for the firstTimeOffer flag
    public void setFirstTimeOffer(boolean firstTimeOffer) {
        this.firstTimeOffer = firstTimeOffer;
    }

    // Method to set the details of the user
    public void setDetails(String username, String password ) {
        this.username = username;
        this.password = password;
        cart = new ShoppingCart(); // Initialize the user's shopping cart
    }

    // Getter method for the username
    public String getUsername() {
        return username;
    }

    // Setter method for the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter method for the password (empty implementation, not secure, just for demonstration)
    public void setPassword(String enteredPassword) {
        // Note: In a real-world scenario, password handling should be secure (e.g., hashing)
    }

    // Getter method for the password
    public String getPassword() {
        return password;
    }
}
