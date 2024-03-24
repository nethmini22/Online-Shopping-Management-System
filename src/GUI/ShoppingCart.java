package GUI;

import Console.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Shopping cart window for displaying and managing user's selected products
public class ShoppingCart extends JFrame {
    private DefaultTableModel cartTableModel;
    private JTable cartTable;
    private JTextArea cartDetailsArea;
    private JLabel discountsLabel;
    private JLabel totalLabel;
    private JLabel firstPurchaseDiscountLabel;
    private JLabel categoryDiscountLabel;
    private JLabel finalTotalLabel;

    // Shopping cart related
    private Map<String, Integer> shoppingCart;
    private List<Product> productList;  // List of products available
    private Map<String, Integer> purchaseHistory;  // Keep track of purchases for each category
    private NumberFormat currencyFormat;  // Currency formatter for displaying prices

    // Constructor for the shopping cart
    public ShoppingCart(List<Product> productList) {
        this.productList = productList;
        this.shoppingCart = new HashMap<>();
        this.purchaseHistory = new HashMap<>();
        this.currencyFormat = NumberFormat.getCurrencyInstance();  // Initialize currencyFormat

        // Create GUI components
        cartTableModel = new DefaultTableModel(new Object[]{"Product", "Quantity", "Price"}, 0);
        cartTable = new JTable(cartTableModel);
        cartTable.setPreferredSize(new Dimension(400, 200));
        cartTable.setRowHeight(40);

        totalLabel = new JLabel("Total: ");
        firstPurchaseDiscountLabel = new JLabel("First Purchase Discount (10%): ");
        categoryDiscountLabel = new JLabel("Three Items in the Same Category Discount (20%): ");
        finalTotalLabel = new JLabel("Final Total: ");

        // Set up the layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        mainPanel.add(scrollPane);
        mainPanel.add(totalLabel);
        mainPanel.add(firstPurchaseDiscountLabel);
        mainPanel.add(categoryDiscountLabel);
        mainPanel.add(finalTotalLabel);

        // Set up the frame
        this.setTitle("Shopping Cart");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 400);
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    // Method to add a product to the shopping cart
    public void addToShoppingCart(Product selectedProduct) {
        shoppingCart.put(selectedProduct.getProductID(), shoppingCart.getOrDefault(selectedProduct.getProductID(), 0) + 1);
        updateCartDetails();
    }

    // Method to update the shopping cart details
    private void updateCartDetails() {
        cartTableModel.setRowCount(0);

        for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = findProductById(productId);

            if (product != null) {
                Object[] rowData = {product.cartDetails(), quantity, product.getPrice()};
                cartTableModel.addRow(rowData);
            }
        }
    }

    // Method to display the shopping cart
    public void displayShoppingCart() {
        updateCartDetails();
        calculateFinalCost();
        this.setVisible(true);
        updateDiscountDetails();  // Update discount labels after displaying the shopping cart
    }

    // Method to calculate the final cost of the items in the shopping cart
    private double calculateFinalCost() {
        double totalCost = 0;

        for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = findProductById(productId);

            if (product != null) {
                totalCost += product.getPrice() * quantity;
            }
        }

        // Apply discounts
        totalCost -= applyFirstPurchaseDiscount(totalCost);
        totalCost -= applyCategoryDiscount();

        updateDiscountDetails();

        return totalCost;
    }

    // Method to apply a discount for the user's first purchase
    private double applyFirstPurchaseDiscount(double totalCost) {
        if (purchaseHistory.isEmpty()) {
            double discount = 0.1 * totalCost;  // 10% discount for the very first purchase
            firstPurchaseDiscountLabel.setText("<html>First Purchase Discount (10%): " + currencyFormat.format(discount) + "</html>");
            return discount;
        }
        return 0;
    }

    // Method to apply a discount for purchasing three or more items in the same category
    private double applyCategoryDiscount() {
        double categoryDiscount = 0;

        for (Map.Entry<String, Integer> entry : purchaseHistory.entrySet()) {
            int quantity = entry.getValue();
            if (quantity >= 3) {
                categoryDiscount += 0.2 * getProductPriceByCategory(entry.getKey()) * quantity;
                categoryDiscountLabel.setText(categoryDiscountLabel.getText() + "<br>Three Items in the same Category Discount (20%): " + currencyFormat.format(categoryDiscount));
            }
        }

        return categoryDiscount;
    }

    // Method to display a message about a discount
    private void displayDiscountMessage(String discountType, double discountAmount) {
        // Unused method
    }

    // Method to calculate the total cost of items in the shopping cart
    private double calculateTotal() {
        double totalCost = 0;

        for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = findProductById(productId);

            if (product != null) {
                totalCost += product.getPrice() * quantity;
            }
        }

        return totalCost;
    }

    // Method to get the price of a product by its category
    private double getProductPriceByCategory(String category) {
        for (Product product : productList) {
            if (category.equals(product.getCategory())) {
                return product.getPrice();
            }
        }
        return 0;
    }

    // Method to clear the shopping cart
    private void clearShoppingCart() {
        shoppingCart.clear();
        updateCartDetails();
    }

    // Method to find a product by its ID
    private Product findProductById(String productId) {
        for (Product product : productList) {
            if (productId.equals(product.getProductID())) {
                return product;
            }
        }
        return null;
    }

    // Method to update discount details labels
    private void updateDiscountDetails() {
        // Update discount details
        double total = calculateTotal();
        double firstPurchaseDiscount = applyFirstPurchaseDiscount(total);
        double categoryDiscount = applyCategoryDiscount();
        double finalTotal = total - firstPurchaseDiscount - categoryDiscount;

        // Update label texts
        totalLabel.setText("Total: " + currencyFormat.format(total) + " £");
        firstPurchaseDiscountLabel.setText("First Purchase Discount (10%): " + currencyFormat.format(firstPurchaseDiscount) + " £");
        categoryDiscountLabel.setText("Three Items in same Category Discount (20%): " + currencyFormat.format(categoryDiscount) + " £");
        finalTotalLabel.setText("Final Total: " + currencyFormat.format(finalTotal) + " £");

        // Repaint the panel to reflect changes
        this.getContentPane().revalidate();
        this.getContentPane();
    }
}
