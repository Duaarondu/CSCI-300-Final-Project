import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderChanger extends JFrame {
    private JPanel panel;
    private JTable orderTable;
    private JButton updateStatusButton;
    private JButton deleteOrderButton;
    private JButton refreshButton;
    private JButton backButton;
    private JComboBox<String> statusComboBox;

    private DefaultTableModel tableModel;

    public OrderChanger() {
        this.setContentPane(panel);
        this.setTitle("Order Manager");
        this.setBounds(600, 200, 700, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupStatusDropdown();
        setupTable();
        loadOrders();
        setupButtons();
    }


    private void setupStatusDropdown() {
        statusComboBox.addItem("Pending");
        statusComboBox.addItem("Preparing");
        statusComboBox.addItem("Out for Delivery");
        statusComboBox.addItem("Completed");
        statusComboBox.addItem("Cancelled");
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(
                new Object[]{"order_id", "customer_id", "order_date", "coupon_code", "status"},
                0
        );
        orderTable.setModel(tableModel);
    }

    private void loadOrders() {
        tableModel.setRowCount(0);

        try {
            String query = "SELECT * FROM Orders";
            PreparedStatement stm = Database.connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getString("order_date"),
                        rs.getString("coupon_code"),
                        rs.getString("status")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading orders: " + ex.getMessage());
        }
    }

    private void setupButtons() {

        refreshButton.addActionListener(e -> loadOrders());

        updateStatusButton.addActionListener(e -> {
            int row = orderTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an order first!");
                return;
            }

            int orderId = (int) tableModel.getValueAt(row, 0);
            String newStatus = (String) statusComboBox.getSelectedItem();

            try {
                String query = "UPDATE Orders SET status = ? WHERE order_id = ?";
                PreparedStatement stm = Database.connection.prepareStatement(query);
                stm.setString(1, newStatus);
                stm.setInt(2, orderId);
                stm.executeUpdate();

                JOptionPane.showMessageDialog(this, "Status updated!");
                loadOrders();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating: " + ex.getMessage());
            }
        });

        deleteOrderButton.addActionListener(e -> {
            int row = orderTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an order first!");
                return;
            }

            int orderId = (int) tableModel.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Delete order #" + orderId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                String query = "DELETE FROM Orders WHERE order_id = ?";
                PreparedStatement stm = Database.connection.prepareStatement(query);
                stm.setInt(1, orderId);
                stm.executeUpdate();

                JOptionPane.showMessageDialog(this, "Order deleted!");
                loadOrders();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new AdminMenu();
        });
    }
}
