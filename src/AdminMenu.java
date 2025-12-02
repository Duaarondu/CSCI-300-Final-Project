import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminMenu extends JDialog {
    private JPanel contentpane;
    private JButton employeeCreateButton;
    private JButton orderChangerButton;
    private JButton accountManagerButton;
    private JButton logOutButton;

    public AdminMenu() {
        this.setContentPane(contentpane);
        this.setTitle("Admin Menu");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logOutButton();
        setUpCreateEmployeeButtonAction();
        setUpOrderChangerButtonAction();
        setUpAccountManagerAction();

    }
    public void setUpCreateEmployeeButtonAction() {
        employeeCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToEmployeeCreator();
            }
        });
    }

    public void goToOrderChanger() {
        this.dispose();
        new OrderChanger();
    }
    public void setUpOrderChangerButtonAction() {
        orderChangerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToOrderChanger();
            }
        });
    }

    public void goToEmployeeCreator() {
        this.dispose();
        new EmployeeCreator();
    }
    public void setUpAccountManagerAction() {
        orderChangerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToOrderChanger();
            }
        });
    }

    public void goToAccountManager() {
        this.dispose();
        new AccountManager();
    }

    public void logOutButton() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }
        });
    }

}

