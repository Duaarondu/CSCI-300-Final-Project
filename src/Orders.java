import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Orders extends JFrame {
    private JButton cancelOrderButton;
    private JButton submitButton;
    private JPanel panel;
    private JButton backButton;
    private JTable orderTable;
    private JLabel totalLabel;

    public Orders() {
        this.setContentPane(this.panel);
        this.setTitle("Orders");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadOrderFromCart();

        this.setupCancelOrderButton();
        this.setUpSubmitButton();
        this.SetUpBackButton();
    }

    private void loadOrderFromCart() {
        // 1. Show same items as in Cart
        orderTable.setModel(Cart.cartTableModel);

        // 2. Calculate total = sum(price * quantity)
        double total = 0.0;
        for (int row = 0; row < Cart.cartTableModel.getRowCount(); row++) {
            Object priceObj = Cart.cartTableModel.getValueAt(row, 2);   // "price" column
            Object qtyObj = Cart.cartTableModel.getValueAt(row, 3);   // "quantity" column

            double price = Double.parseDouble(priceObj.toString());
            int quantity = Integer.parseInt(qtyObj.toString());

            total += price * quantity;
        }
        if (totalLabel != null) {
            totalLabel.setText(String.format("Total: $%.2f", total));
        }
    }



    public void setupCancelOrderButton() {
        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 1. Confirm cancel (optional)
                int confirm = JOptionPane.showConfirmDialog(
                        Orders.this,
                        "Are you sure you want to cancel your entire order?",
                        "Cancel Order",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                // 2. CLEAR the cart
                Cart.cartTableModel.setRowCount(0);

                // 3. Update Orders table UI
                orderTable.setModel(Cart.cartTableModel);

                // 4. Reset total
                if (totalLabel != null) {
                    totalLabel.setText("Total: $0.00");
                }

                // 5. Feedback
                JOptionPane.showMessageDialog(
                        Orders.this,
                        "Order has been canceled."
                );

            }
        });
    }

    public void setUpSubmitButton(){
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // If cart is empty
                if (Cart.cartTableModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(
                            Orders.this,
                            "No items to submit.",
                            "Empty Order",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                //  Calculate total directly; similar code to line 37
                double total = 0.0;
                for (int row = 0; row < Cart.cartTableModel.getRowCount(); row++) {
                    double price = Double.parseDouble(Cart.cartTableModel.getValueAt(row, 2).toString());
                    int qty = Integer.parseInt(Cart.cartTableModel.getValueAt(row, 3).toString());
                    total += price * qty;
                }

                try {
                    Connection conn = Database.connection;

                    // 1. Insert into orders table
                    String orderSql = "INSERT INTO orders (total_amount) VALUES (?)";
                    PreparedStatement orderStmt =
                            conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

                    orderStmt.setDouble(1, total);
                    orderStmt.executeUpdate();

                    // 2. Get new order_id
                    int orderId = -1;
                    ResultSet keys = orderStmt.getGeneratedKeys();
                    if (keys.next()) {
                        orderId = keys.getInt(1);
                    }
                    keys.close();
                    orderStmt.close();

                    if (orderId == -1) {
                        JOptionPane.showMessageDialog(Orders.this,
                                "Error: Could not create order.",
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 3. Insert each item from cart into order_items table
                    String itemSql =
                            "INSERT INTO order_items (order_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
                    PreparedStatement itemStmt = conn.prepareStatement(itemSql);

                    for (int row = 0; row < Cart.cartTableModel.getRowCount(); row++) {
                        int itemId = Integer.parseInt(Cart.cartTableModel.getValueAt(row, 0).toString());
                        double price = Double.parseDouble(Cart.cartTableModel.getValueAt(row, 2).toString());
                        int quantity = Integer.parseInt(Cart.cartTableModel.getValueAt(row, 3).toString());

                        itemStmt.setInt(1, orderId);
                        itemStmt.setInt(2, itemId);
                        itemStmt.setInt(3, quantity);
                        itemStmt.setDouble(4, price);
                        itemStmt.executeUpdate();
                    }

                    itemStmt.close();

                    // 4. Clear cart
                    Cart.cartTableModel.setRowCount(0);
                    orderTable.setModel(Cart.cartTableModel);
                    totalLabel.setText("Total: $0.00");

                    // 5. Tell user the order was saved
                    JOptionPane.showMessageDialog(Orders.this,
                            "Order submitted! Order ID: " + orderId);

                    // 6. Go back to catalog
                    dispose();
                    new Catalog().setVisible(true);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            Orders.this,
                            "Error submitting order: " + ex.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }



            }
        });

    } //remove if needed

    public void SetUpBackButton() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Catalog().setVisible(true);

            }
        });

    }


}

//we have order_items table in mySQL workbench  which is empty
