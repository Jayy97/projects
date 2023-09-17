import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * 
 * A GUI class for deleting a product from the database.
 */

public class ProductDelete extends JFrame implements ActionListener {
    /**
     * 
     * Creates a new instance of ProductDelete frame and sets its properties.
     */
    private JTextField txtProdID;

    public ProductDelete() {
        setTitle("Delete Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create welcome message label
        JLabel welcomeLabel = new JLabel(
                "<html>Please enter the Product ID <br> &nbsp; &nbsp; &nbsp; that you wish to delete</html>");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        welcomeLabel.setForeground(new Color(0x009688));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        formPanel.setBackground(new Color(0xFFFFFF));
        JLabel idLabel = new JLabel("Product ID:");
        txtProdID = new JTextField();
        txtProdID.setColumns(10);
        formPanel.add(idLabel);
        formPanel.add(txtProdID);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(0xFFFFFF));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(0x009688));
        deleteButton.setForeground(new Color(0xFFFFFF));
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(this);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(0x009688));
        cancelButton.setForeground(new Color(0xFFFFFF));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> {
            dispose();
            new ProductsPage().setVisible(true);
        });
        buttonPanel.add(deleteButton);
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
        container.add(welcomeLabel, BorderLayout.NORTH);
        container.add(wrapperPanel, BorderLayout.CENTER);
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        deleteProduct();
    }

    private void deleteProduct() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/purchases";
            String user = "root";
            String password = "Jamie1997";
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM product WHERE ProdID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtProdID.getText());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("ProdName");
                int option = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete the following product: '" + name + "'?", "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    sql = "DELETE FROM product WHERE ProdID = ?";
                    pstmt.close();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, txtProdID.getText());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                        dispose();
                        new ProductsPage().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "No Product found with the specified ID.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No Product found with the specified ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the Product: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "An error occurred while closing the connection: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}