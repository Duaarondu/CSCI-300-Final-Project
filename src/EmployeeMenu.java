import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeMenu extends JFrame {
    private JPanel panel;
    private JLabel welcomeLabel;
    private JButton createItemButton;
    private JButton viewCustomersButton;
    private JButton viewOrdersButton;
    private JButton createCouponButton;
    private JButton logoutButton;

    private String employeeUsername;

    // 🔹 Main constructor: this is the one we will use
    public EmployeeMenu(String username) {
        this.employeeUsername = username;

        this.setContentPane(panel);
        this.setTitle("Employee Menu");
        this.setBounds(600, 200, 420, 320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeLabel.setText("Welcome, " + username);
        setUpButtons();

        this.setVisible(true);
    }

    // 🔹 Optional: remove this entirely, or make it delegate:
    public EmployeeMenu() {
        this("Employee"); // or some default if you really want a no-arg
    }

    private void setUpButtons() {
        createItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ItemCreator();
            }
        });

        viewCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerList();
            }
        });

        viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderList();
            }
        });

        createCouponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CouponCreator();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignIn();
            }
        });
    }
}
