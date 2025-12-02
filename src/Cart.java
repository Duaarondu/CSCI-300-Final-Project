import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel; //just added

public class Cart extends JFrame {
    private JPanel panel;
    private JTable cartTable;
    private JButton checkoutButton;
    private JButton backButton;
    private JButton removeButton;

    public static DefaultTableModel cartTableModel =
            new DefaultTableModel(
                    new Object[]{"item_id", "name", "price", "quantity"}, 0
            ); // just added

    public Cart() {
        this.setContentPane(this.panel);
        cartTable.setModel(cartTableModel); //just added

        this.setTitle("Cart");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setupCheckoutButton();
        this.SetUpBackButton();
        this.setupRemoveButton();


    }

    public void setupCheckoutButton() { //THis code has checkout go straight to orders with total amount included
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToOrders();

            }
        });
    }
    public void goToOrders(){
        this.dispose();
        new Orders();
    }

    public void setupRemoveButton() {
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // STEP A: Check if a row is selected
                int selectedRow = cartTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(
                            Cart.this,
                            "Please select an item to remove.",
                            "No item selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                cartTableModel.removeRow(selectedRow);

                // STEP C: UI feedback (optional)
                JOptionPane.showMessageDialog(
                        Cart.this,
                        "Item removed from cart."
                );

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
