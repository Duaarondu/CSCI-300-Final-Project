import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    private JPanel contentPane;

    private JPanel panel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton createButton;

    public Register() {
        this.setContentPane(this.panel);
        this.setTitle("Employee");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SetUPCreateButton(); // Button to create account for new users

    }

    public void SetUPCreateButton() { //Lets the customer create their new account to go straight to the catalog
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

