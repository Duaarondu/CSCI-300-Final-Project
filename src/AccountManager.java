import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountManager extends JFrame {
    private JPanel panel;
    private JPanel topPanel;
    private JTable accountTable;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;
    private JComboBox<String> typeCombo;

    private DefaultTableModel tableModel;

    public AccountManager() {
        this.setContentPane(panel);
        this.setTitle("Account Manager");
        this.setBounds(600, 200, 800, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupTypeCombo();
        setupTable();
        loadAccounts();
        setupButtons();
    }

    private void setupTypeCombo() {
        typeCombo.addItem("Customers");
        typeCombo.addItem("Employees");

        typeCombo.addActionListener(e -> loadAccounts());
    }

    private void setupTable() {
        tableModel = new DefaultTableModel();
        accountTable.setModel(tableModel);
    }

    private void loadAccounts() {
        tableModel.setRowCount(0);

        try {
            if (typeCombo.getSelectedItem().equals("Customers")) {
                tableModel.setColumnIdentifiers(
                        new Object[]{"customer_id", "username", "password_hash", "full_name", "address"}
                );

                PreparedStatement stm = Database.connection.prepareStatement("SELECT * FROM Customers");
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getInt("customer_id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("full_name"),
                            rs.getString("address")
                    });
                }

            } else {
                tableModel.setColumnIdentifiers(
                        new Object[]{"employee_id", "username", "password_hash", "full_name"}
                );

                PreparedStatement stm = Database.connection.prepareStatement("SELECT * FROM Employees");
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getInt("employee_id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("full_name")
                    });
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void setupButtons() {

        refreshButton.addActionListener(e -> loadAccounts());

        updateButton.addActionListener(e -> {
            int row = accountTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an account first!");
                return;
            }

            try {
                if (typeCombo.getSelectedItem().equals("Customers")) {
                    updateCustomer(row);
                } else {
                    updateEmployee(row);
                }

                loadAccounts();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            int row = accountTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an account first!");
                return;
            }

            try {
                if (typeCombo.getSelectedItem().equals("Customers")) {
                    deleteCustomer(row);
                } else {
                    deleteEmployee(row);
                }

                loadAccounts();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new AdminMenu();
        });
    }

    private void updateCustomer(int row) throws Exception {
        int id = (int) tableModel.getValueAt(row, 0);
        String username = (String) tableModel.getValueAt(row, 1);
        String pass = (String) tableModel.getValueAt(row, 2);
        String name = (String) tableModel.getValueAt(row, 3);
        String address = (String) tableModel.getValueAt(row, 4);

        PreparedStatement stm = Database.connection.prepareStatement(
                "UPDATE Customers SET username=?, password_hash=?, full_name=?, address=? WHERE customer_id=?"
        );
        stm.setString(1, username);
        stm.setString(2, pass);
        stm.setString(3, name);
        stm.setString(4, address);
        stm.setInt(5, id);
        stm.executeUpdate();

        JOptionPane.showMessageDialog(this, "Customer updated!");
    }

    private void updateEmployee(int row) throws Exception {
        int id = (int) tableModel.getValueAt(row, 0);
        String username = (String) tableModel.getValueAt(row, 1);
        String pass = (String) tableModel.getValueAt(row, 2);
        String name = (String) tableModel.getValueAt(row, 3);

        PreparedStatement stm = Database.connection.prepareStatement(
                "UPDATE Employees SET username=?, password_hash=?, full_name=? WHERE employee_id=?"
        );
        stm.setString(1, username);
        stm.setString(2, pass);
        stm.setString(3, name);
        stm.setInt(4, id);
        stm.executeUpdate();

        JOptionPane.showMessageDialog(this, "Employee updated!");
    }

    private void deleteCustomer(int row) throws Exception {
        int id = (int) tableModel.getValueAt(row, 0);

        PreparedStatement stm = Database.connection.prepareStatement(
                "DELETE FROM Customers WHERE customer_id=?"
        );
        stm.setInt(1, id);
        stm.executeUpdate();

        JOptionPane.showMessageDialog(this, "Customer deleted!");
    }

    private void deleteEmployee(int row) throws Exception {
        int id = (int) tableModel.getValueAt(row, 0);

        PreparedStatement stm = Database.connection.prepareStatement(
                "DELETE FROM Employees WHERE employee_id=?"
        );
        stm.setInt(1, id);
        stm.executeUpdate();

        JOptionPane.showMessageDialog(this, "Employee deleted!");

        backButton.addActionListener(e -> {
            dispose();
            new AdminMenu();
        });
    }

}
