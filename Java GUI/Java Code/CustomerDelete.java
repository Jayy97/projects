import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * 
 * A class representing the GUI for deleting a customer from the database.
 */
public class CustomerDelete extends JFrame implements ActionListener {
    /**
     * 
     * Text field for entering the ID of the customer to be deleted.
     */
    private JTextField txtCustID;

    /**
     * 
     * Constructs a new CustomerDelete object, setting up the GUI for deleting a
     * customer.
     * Sets the title, size and location of the JFrame.
     */

    public CustomerDelete() {
        setTitle("Delete Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create welcome message label
        JLabel welcomeLabel = new JLabel(
                "<html>Please enter the Customer ID <br> &nbsp; &nbsp; &nbsp; that you wish to delete</html>");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        welcomeLabel.setForeground(new Color(0x009688));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        formPanel.setBackground(new Color(0xFFFFFF));
        JLabel idLabel = new JLabel("Customer ID:");
        txtCustID = new JTextField();
        txtCustID.setColumns(10);
        formPanel.add(idLabel);
        formPanel.add(txtCustID);

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
            new CustomersPage().setVisible(true);
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
        deleteCustomer();
    }

    private void deleteCustomer() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/purchases";
            String user = "root";
            String password = "Jamie1997";
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM customer WHERE CustID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtCustID.getText());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("FirstName");
                String sname = rs.getString("LastName");
                int option = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete customer '" + name + " " + sname + "'?", "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    sql = "DELETE FROM customer WHERE CustID = ?";
                    pstmt.close();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, txtCustID.getText());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
                        dispose();
                        new CustomersPage().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "No customer found with the specified ID.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No customer found with the specified ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the customer: " + ex.getMessage(),
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