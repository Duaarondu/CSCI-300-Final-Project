import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

                // 1. Confirm cancel
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

            }
        });

    }


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
