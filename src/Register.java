import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class Register extends JFrame {
    private JPanel panel;
    private JButton createButton;
    private JTextField usernameTF;
    private JTextField nameTF;
    private JTextField addressTF;
    private JPasswordField passwordField;

    public Register() {
        this.setContentPane(this.panel);
        this.setTitle("Register");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreateAccount();
            }
        });
    }
    private void handleCreateAccount() {
        try {
            String username = usernameTF.getText();
            String name = nameTF.getText();
            String address = addressTF.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isEmpty() || name.isEmpty() || address.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields.");
                return;
            }

            String query = "INSERT INTO customers (username, password_hash, full_name, address) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = Database.connection.prepareStatement(query);

            stm.setString(1, username);
            stm.setString(3, name);
            stm.setString(4, address);
            stm.setString(2, password);

            stm.executeUpdate();

            JOptionPane.showMessageDialog(this, "Account created!");
            this.dispose();
            new SignIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


}
