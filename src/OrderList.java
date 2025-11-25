import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderList extends JFrame {
    private JPanel panel;
    private JTable table;
    private JButton refreshButton;

    public OrderList() {
        this.setContentPane(panel);
        this.setTitle("Order List");
        this.setBounds(640, 220, 700, 450);
        this.setVisible(true);

        refreshButton.addActionListener(e -> loadData());
        loadData();


    }

    private void loadData() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Order ID", "Customer Name", "Order Date", "Coupon", "Status"});

        try {
            String sql = "SELECT o.order_id, c.full_name, o.order_date, o.coupon_code, o.status " +
                    "FROM orders o JOIN customers c ON o.customer_id = c.customer_id " +
                    "ORDER BY c.full_name ASC";
            PreparedStatement ps = Database.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("order_id"),
                        rs.getString("full_name"),
                        rs.getTimestamp("order_date"),
                        rs.getString("coupon_code"),
                        rs.getString("status")
                });
            }
            table.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
