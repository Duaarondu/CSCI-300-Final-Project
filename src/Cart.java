import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cart extends JFrame {
    private JPanel panel;
    private JTable cartTable;
    private JButton checkoutButton;

    public Cart() {
        this.setContentPane(this.panel);
        this.setTitle("Cart");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupCheckoutButton();
    }

    public void setupCheckoutButton() {
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void Set

}
