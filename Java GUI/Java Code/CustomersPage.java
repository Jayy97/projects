import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * The CustomersPage class represents the customers page window of the Customer
 * Invoice Management System.
 * 
 * It extends the JFrame class to create a graphical user interface.
 */
public class CustomersPage extends JFrame {
    /**
     * 
     * Constructs a new CustomersPage object, which creates the customers page
     * window.
     */

    public CustomersPage() {
        setTitle("Customers Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        // Create welcome message label
        JLabel welcomeLabel = new JLabel("Customers Page");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(0x009688));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBackground(new Color(0xFFFFFF));
        JButton createButton = new JButton("Create Customer");
        createButton.setBackground(new Color(0x009688));
        createButton.setForeground(new Color(0xFFFFFF));
        createButton.setFocusPainted(false);
        createButton.setPreferredSize(new Dimension(120, 40));
        JButton retrieveButton = new JButton("Retrieve Customer");
        retrieveButton.setBackground(new Color(0x009688));
        retrieveButton.setForeground(new Color(0xFFFFFF));
        retrieveButton.setFocusPainted(false);
        retrieveButton.setPreferredSize(new Dimension(120, 40));
        JButton updateButton = new JButton("Update Customer");
        updateButton.setBackground(new Color(0x009688));
        updateButton.setForeground(new Color(0xFFFFFF));
        updateButton.setFocusPainted(false);
        updateButton.setPreferredSize(new Dimension(120, 40));
        JButton deleteButton = new JButton("Delete Customer");
        deleteButton.setBackground(new Color(0x009688));
        deleteButton.setForeground(new Color(0xFFFFFF));
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(new Dimension(120, 40));
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
        container.add(welcomeLabel, BorderLayout.NORTH);
        container.add(wrapperPanel, BorderLayout.CENTER);

        /**
         * 
         * Adds an action listener to the createButton, which creates a new customer
         * object and opens a window to create it.
         * When the createButton is clicked, the current window is disposed and a new
         * CustomerCreate window is created and set to visible.
         * 
         * @param createButton the button that triggers the creation of a new customer
         *                     object
         */
        // Add action listener for createButton

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CustomerCreate createCustomer = new CustomerCreate();
                createCustomer.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the retrieveButton, which retrieves an existing
         * customer object and opens a window to view its details.
         * When the retrieveButton is clicked, the current window is disposed and a new
         * CustomerRetrieve window is created and set to visible.
         * 
         * @param retrieveButton the button that triggers the retrieval of an existing
         *                       customer object
         */
        // Add action listener for retrieveButton
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CustomerRetrieve retrieveCustomer = new CustomerRetrieve();
                retrieveCustomer.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the updateButton, which updates an existing
         * customer object and opens a window to edit its details.
         * When the updateButton is clicked, the current window is disposed and a new
         * CustomerUpdate window is created and set to visible.
         * 
         * @param updateButton the button that triggers the update of an existing
         *                     customer object
         */
        // Add action listener for updateButton
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CustomerUpdate updateButton = new CustomerUpdate();
                updateButton.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the deleteButton, which deletes an existing
         * customer object and opens a window to confirm the deletion.
         * When the deleteButton is clicked, the current window is disposed and a new
         * CustomerDelete window is created and set to visible.
         * 
         * @param deleteButton the button that triggers the deletion of an existing
         *                     customer object
         */
        // Add action listener for deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CustomerDelete deleteButton = new CustomerDelete();
                deleteButton.setVisible(true);
            }
        });

        // Create main menu button panel
        JPanel mainMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainMenuPanel.setPreferredSize(new Dimension(300, 40));
        mainMenuPanel.setBackground(new Color(0xFFFFFF));
        JButton mainMenuButton = new JButton("Main menu");
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
        CustomersPage customersPage = new CustomersPage();
        customersPage.setVisible(true);
    }
}