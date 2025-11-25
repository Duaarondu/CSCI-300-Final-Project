import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerList extends JFrame {
    private JPanel panel;
    private JTable table;
    private JButton refreshButton;

    public CustomerList() {
        this.setContentPane(panel);
        this.setTitle("Customer List");
        this.setBounds(650, 250, 600, 400);
        this.setVisible(true);

        refreshButton.addActionListener(e -> loadData());
        loadData();
    }

    private void loadData() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Username", "Full Name", "Address"});

        try {
            String sql = "SELECT customer_id, username, full_name, address FROM customers";
            PreparedStatement ps = Database.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("customer_id"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getString("address")
                });
            }
            table.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
