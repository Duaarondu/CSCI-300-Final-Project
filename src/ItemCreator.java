import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemCreator extends JFrame {
    private JPanel panel;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField qtyField;
    private JButton addItemButton;
    private JLabel itemLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;

    public ItemCreator() {
        this.setContentPane(panel);
        this.setTitle("Create Item");
        this.setBounds(650, 250, 360, 220);
        this.setVisible(true);
        itemLabel.setText("Item");
        priceLabel.setText("Price");
        quantityLabel.setText("Quantity");

        setUpButton();
    }

    private void setUpButton() {
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createItem();
            }
        });
    }

    private void createItem() {
        String name = nameField.getText().trim();
        String price = priceField.getText().trim();
        String qty = qtyField.getText().trim();

        if (name.isEmpty() || price.isEmpty() || qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields required.");
            return;
        }

        try {
            String sql = "INSERT INTO items (name, price, quantity_in_stock) VALUES (?, ?, ?)";
            PreparedStatement ps = Database.connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, Double.parseDouble(price));
            ps.setInt(3, Integer.parseInt(qty));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Item created!");
            nameField.setText("");
            priceField.setText("");
            qtyField.setText("");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Price and qty must be numeric.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
