import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class EmployeeCreator extends JFrame {
    private JPanel panel;
    private JTextField employeeNameTF;
    private JButton createEmployeeButton;
    private JTextField passwordTF;
    private JTextField usernameTF;
    private JButton backButton;

    public EmployeeCreator() {
    this.setContentPane(this.panel);
    this.setTitle("Employee Creator");
    this.setBounds(600, 200, 300, 300);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SetUpBackButton();
     setUpCreateEmployeeButton();
    }
    public void setUpCreateEmployeeButton() {
        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEmployee();
            }
        });
    }

    public void createEmployee() {
        try {
            String query = "INSERT INTO Employees (username, password_hash, full_name) VALUES (?, ?, ?)";
            PreparedStatement stm = Database.connection.prepareStatement(query);

            stm.setString(3, employeeNameTF.getText());
            stm.setString(1, usernameTF.getText());
            stm.setString(2, passwordTF.getText());
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "The new employee was added to the database!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            throw new RuntimeException(e);
        }
    }
    public void SetUpBackButton() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminMenu().setVisible(true);

            }
        });

    }



}