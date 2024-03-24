package GUI;

import Console.Product;
import Console.WestminsterShoppingManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// GUI class for the Westminster Shopping Centre
public class gui extends JFrame {
    private DefaultTableModel tableModel;
    private JTable productTable;
    private JTextArea productDetailsArea;
    private JComboBox<String> categoryComboBox;
    private JButton addToCartButton;
    private JButton shoppingCartButton;
    private WestminsterShoppingManager shoppingManager;
    private List<Product> productList;
    private ShoppingCart shoppingCart;

    // Constructor to initialize the GUI
    public gui(WestminsterShoppingManager shoppingManager, List<Product> productList) {
        this.shoppingManager = shoppingManager;
        this.productList = productList;

        initComponents();
        setupLayout();
        setupEventHandlers();

        // Set up the frame
        this.setTitle("Westminster Shopping Centre");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);

        // Populate the table with initial data
        populateTable("All");
    }

    // Initialize GUI components
    private void initComponents() {
        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        tableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Category", "Price ($)", "Info"}, 0);
        productTable = new JTable(tableModel);
        productTable.setRowSelectionAllowed(true);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.setRowHeight(40);
        productTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        productDetailsArea = new JTextArea("Selected Product Details");
        addToCartButton = new JButton("Add to Shopping Cart");
        shoppingCartButton = new JButton("Shopping Cart");
        shoppingCart = new ShoppingCart(productList);
    }

    // Set up the layout of the GUI
    private void setupLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        mainPanel.add(new JLabel("Select Product Category:"));
        mainPanel.add(categoryComboBox);
        mainPanel.add(shoppingCartButton);
        mainPanel.add(new JScrollPane(productTable));
        mainPanel.add(productDetailsArea);
        mainPanel.add(addToCartButton);

        this.setContentPane(mainPanel);
    }

    // Set up event handlers for GUI components
    private void setupEventHandlers() {
        categoryComboBox.addActionListener(e -> filterProductsByCategory((String) categoryComboBox.getSelectedItem()));

        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                displayProductDetails(selectedRow);
            }
        });

        addToCartButton.addActionListener(e -> addToCart());

        shoppingCartButton.addActionListener(e -> showShoppingCart());
    }

    // Show the shopping cart
    private void showShoppingCart() {
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(productList);
        }
        shoppingCart.displayShoppingCart();
    }

    // Filter products based on selected category
    private void filterProductsByCategory(String category) {
        populateTable(category);
    }

    // Display details of the selected product
    private void displayProductDetails(int rowIndex) {
        Product selectedProduct = productList.get(rowIndex);
        productDetailsArea.setText(selectedProduct.toString());
    }

    // Populate the table with products based on the selected category
    private void populateTable(String category) {
        tableModel.setRowCount(0);

        List<Product> filteredProducts;
        if ("All".equals(category)) {
            filteredProducts = productList;
        } else {
            filteredProducts = productList.stream()
                    .filter(p -> category.equals(p.getCategory()))
                    .toList();
        }

        for (Product product : filteredProducts) {
            Object[] rowData = {product.getProductID(), product.getProductName(), product.getCategory(), product.getPrice(), product.showProductDetails()};
            tableModel.addRow(rowData);
        }
    }

    // Add the selected product to the shopping cart
    private void addToCart() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            Product selectedProduct = productList.get(selectedRow);
            shoppingCart.addToShoppingCart(selectedProduct);
            JOptionPane.showMessageDialog(this, "Product added to the shopping cart.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product from the table.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();  // Initialize with your actual product data
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();  // Initialize with your shopping manager data

        SwingUtilities.invokeLater(() -> new gui(shoppingManager, productList));
    }
}
