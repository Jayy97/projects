import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * The ProductsPage class represents the products page window of the Customer
 * Invoice Management System.
 * 
 * It extends the JFrame class to create a graphical user interface.
 */
public class ProductsPage extends JFrame {
    /**
     * 
     * Constructs a new ProductsPage object, which creates the products page window.
     */
    public ProductsPage() {
        setTitle("Products Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        // Create title label
        JLabel titleLabel = new JLabel("Products Page");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0x009688));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBackground(new Color(0xFFFFFF));
        JButton createButton = new JButton("Create Product");
        createButton.setBackground(new Color(0x009688));
        createButton.setForeground(new Color(0xFFFFFF));
        createButton.setFocusPainted(false);
        createButton.setPreferredSize(new Dimension(150, 50));
        JButton retrieveButton = new JButton("Retrieve Product");
        retrieveButton.setBackground(new Color(0x009688));
        retrieveButton.setForeground(new Color(0xFFFFFF));
        retrieveButton.setFocusPainted(false);
        retrieveButton.setPreferredSize(new Dimension(150, 50));
        JButton updateButton = new JButton("Update Product");
        updateButton.setBackground(new Color(0x009688));
        updateButton.setForeground(new Color(0xFFFFFF));
        updateButton.setFocusPainted(false);
        updateButton.setPreferredSize(new Dimension(150, 50));
        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setBackground(new Color(0x009688));
        deleteButton.setForeground(new Color(0xFFFFFF));
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(new Dimension(150, 50));
        buttonPanel.add(createButton);
        buttonPanel.add(retrieveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Wrap button panel in another panel
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(new Color(0xFFFFFF));
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        wrapperPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add components to the frame
        Container container = getContentPane();
        container.setLayout(new BorderLayout(0, 20));
        container.setBackground(new Color(0xFFFFFF));
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(wrapperPanel, BorderLayout.CENTER);

        /**
         * 
         * Adds an action listener to the createButton, which creates a new product
         * object and opens a window to enter its details.
         * When the createButton is clicked, the current window is disposed and a new
         * ProductCreate window is created and set to visible.
         * 
         * @param createButton the button that triggers the creation of a new product
         *                     object
         */
        // Add action listener for createButton
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductCreate createProduct = new ProductCreate();
                createProduct.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the retrieveButton, which retrieves an existing
         * product object and opens a window to display its details.
         * When the retrieveButton is clicked, the current window is disposed and a new
         * ProductRetrieve window is created and set to visible.
         * 
         * @param retrieveButton the button that triggers the retrieval of an existing
         *                       product object
         */
        // Add action listener for retrieveButton
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductRetrieve retrieveProduct = new ProductRetrieve();
                retrieveProduct.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the updateButton, which updates an existing
         * product object and opens a window to modify its details.
         * When the updateButton is clicked, the current window is disposed and a new
         * ProductUpdate window is created and set to visible.
         * 
         * @param updateButton the button that triggers the update of an existing
         *                     product object
         */
        // Add action listener for updateButton
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductUpdate updateProduct = new ProductUpdate();
                updateProduct.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the deleteButton, which deletes an existing
         * product object and opens a window to confirm the deletion.
         * When the deleteButton is clicked, the current window is disposed and a new
         * ProductDelete window is created and set to visible.
         * 
         * @param deleteButton the button that triggers the deletion of an existing
         *                     product object
         */
        // Add action listener for deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductDelete deleteProduct = new ProductDelete();
                deleteProduct.setVisible(true);
            }
        });

        // Create main menu button panel
        JPanel mainMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenuPanel.setPreferredSize(new Dimension(300, 40));
        mainMenuPanel.setBackground(new Color(0xFFFFFF));
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBackground(new Color(0x009688));
        mainMenuButton.setForeground(new Color(0xFFFFFF));
        mainMenuButton.setFocusPainted(false);
        mainMenuButton.addActionListener(e -> {
            dispose();
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });
        mainMenuPanel.add(mainMenuButton);

        // Add main menu button panel to the bottom of the frame
        container.add(mainMenuPanel, BorderLayout.SOUTH);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ProductsPage productsPage = new ProductsPage();
        productsPage.setVisible(true);
    }
}