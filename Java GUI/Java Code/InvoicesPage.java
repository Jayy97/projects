import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Constructs a new InvoicesPage object, which is a JFrame that displays a
 * window for managing invoices.
 * The title of the window is set to "Invoices Page". The default close
 * operation is set to exit on close.
 * The size of the window is set to 1000 x 500 pixels. The location of the
 * window is set to the center of the screen.
 */
public class InvoicesPage extends JFrame {
    public InvoicesPage() {
        setTitle("Invoices Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        // Create welcome message label
        JLabel welcomeLabel = new JLabel("Welcome to the Invoices Page");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(0x009688));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBackground(new Color(0xFFFFFF));
        JButton createButton = new JButton("Create Invoice");
        createButton.setBackground(new Color(0x009688));
        createButton.setForeground(new Color(0xFFFFFF));
        createButton.setFocusPainted(false);
        createButton.setPreferredSize(new Dimension(200, 100));
        JButton retrieveButton = new JButton("Retrieve Invoice");
        retrieveButton.setBackground(new Color(0x009688));
        retrieveButton.setForeground(new Color(0xFFFFFF));
        retrieveButton.setFocusPainted(false);
        retrieveButton.setPreferredSize(new Dimension(200, 100));
        JButton updateButton = new JButton("Update Invoice");
        updateButton.setBackground(new Color(0x009688));
        updateButton.setForeground(new Color(0xFFFFFF));
        updateButton.setFocusPainted(false);
        updateButton.setPreferredSize(new Dimension(200, 100));
        JButton deleteButton = new JButton("Delete Invoice");
        deleteButton.setBackground(new Color(0x009688));
        deleteButton.setForeground(new Color(0xFFFFFF));
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(new Dimension(200, 100));
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
         * Adds an action listener to the createButton object, which is triggered when
         * the user clicks the button.
         * When the button is clicked, the current window is disposed, and a new
         * InvoiceCreate object is created and made visible.
         * 
         * @param e the action event that triggered the listener
         */
        // Add action listener for createButton
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InvoiceCreate createInvoice = new InvoiceCreate();
                createInvoice.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the retrieveButton object, which is triggered when
         * the user clicks the button.
         * When the button is clicked, the current window is disposed, and a new
         * InvoiceRetrieve object is created and made visible.
         * 
         * @param e the action event that triggered the listener
         */
        // Add action listener for retrieveButton
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InvoiceRetrieve retrieveInvoice = new InvoiceRetrieve();
                retrieveInvoice.setVisible(true);
            }
        });

        /**
         * 
         * Adds an action listener to the updateButton object, which is triggered when
         * the user clicks the button.
         * When the button is clicked, the current window is disposed, and a new
         * InvoiceUpdate object is created and made visible.
         * 
         * @param e the action event that triggered the listener
         */
        // Add action listener for updateButton
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InvoiceUpdate updateInvoice = new InvoiceUpdate();
                updateInvoice.setVisible(true);
            }
        });

        // Add action listener for deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InvoiceDelete deleteInvoice = new InvoiceDelete();
                deleteInvoice.setVisible(true);
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
        InvoicesPage invoicesPage = new InvoicesPage();
        invoicesPage.setVisible(true);
    }
}