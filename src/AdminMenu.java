import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminMenu extends JDialog {
    private JPanel contentpane;
    private JButton employeeCreateButton;
    private JButton exitButton;

    public AdminMenu() {
        this.setContentPane(contentpane);
        this.setTitle("Admin Menu");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUpCreateEmployeeButtonAction();

    }
    public void setUpCreateEmployeeButtonAction() {
        employeeCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToEmployeeCreator();
            }
        });
    }
    public void goToEmployeeCreator() {
        this.dispose();
        new EmployeeCreator();
    }
}

