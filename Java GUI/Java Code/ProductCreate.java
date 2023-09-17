import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

/**
 * 
 * A GUI class for creating a new product by entering the product details.
 */

public class ProductCreate extends JFrame implements ActionListener {
    /**
     * 
     * Text fields to enter the product name, cost and stock quantity.
     */
    private JTextField prodNameField, prodCostField, prodStockField;

    /**
     * 
     * Creates the "Create Product" frame with appropriate properties and
     * components.
     * 
     * Sets title, default close operation, size and location of the frame.
     * 
     * Initializes the text fields for product name, cost and stock quantity.
     */

    public ProductCreate() {
        setTitle("Create Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(new Color(0xFFFFFF));
        JLabel prodNameLabel = new JLabel("Product Name:");
        prodNameField = new JTextField();
        JLabel prodCostLabel = new JLabel("Product Cost:");
        prodCostField = new JTextField();
        JLabel prodStockLabel = new JLabel("Product Stock:");
        prodStockField = new JTextField();
        formPanel.add(prodNameLabel);
        formPanel.add(prodNameField);
        formPanel.add(prodCostLabel);
        formPanel.add(prodCostField);
        formPanel.add(prodStockLabel);
        formPanel.add(prodStockField);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(0xFFFFFF));
        JButton createButton = new JButton("Create");
        createButton.setBackground(new Color(0x009688));
        createButton.setForeground(new Color(0xFFFFFF));
        createButton.setFocusPainted(false);
        createButton.addActionListener(this);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(0x009688));
        cancelButton.setForeground(new Color(0xFFFFFF));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> {
            dispose();
            new ProductsPage().setVisible(true);
        });
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        // Wrap form panel and button panel in another panel
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(new Color(0xFFFFFF));
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        wrapperPanel.add(formPanel, BorderLayout.CENTER);
        wrapperPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add components to the frame
        Container container = getContentPane();
        container.setLayout(new BorderLayout(0, 20));
        container.setBackground(new Color(0xFFFFFF));
        container.add(wrapperPanel, BorderLayout.CENTER);
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create")) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                String url = "jdbc:mysql://localhost:3306/purchases";
                String user = "root";
                String password = "Jamie1997";
                conn = DriverManager.getConnection(url, user, password);

                // Get the new product details from the GUI inputs
                String prodName = prodNameField.getText();
                String prodCost = prodCostField.getText();
                String prodStock = prodStockField.getText();

                // Prepare the SQL statement with parameters for the new product data
                String sql = "INSERT INTO product (ProdName, ProdCost, ProdStock) " +
                        "VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);

                // Set the values for the parameters in the SQL statement
                pstmt.setString(1, prodName);
                pstmt.setString(2, prodCost);
                pstmt.setString(3, prodStock);

                // Execute the SQL statement to insert the new customer data
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "A new product has been added to the database.", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new ProductsPage().setVisible(true);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error inserting new product: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error closing database resources: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
