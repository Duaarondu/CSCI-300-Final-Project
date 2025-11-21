import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainMenu extends JDialog {
    private JPanel contentPane;
    private JButton customerButton;
    private JButton employeeButton;
    private JButton adminButton;

    public MainMenu() {
        this.setContentPane(contentPane);
        this.setTitle("Main Menu");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUpCustomerButtonAction();
        setUpEmployeeButtonAction();
        setUpAdminButtonAction();
    }
    public void setUpCustomerButtonAction() {
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToCustomer();
            }
        });
    }

    public void setUpEmployeeButtonAction() {
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToEmployee();
            }
        });
    }

    public void setUpAdminButtonAction() {
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToAdmin();
            }
        });
    }

    public void goToCustomer() {
        this.dispose();
        new Customer();
    }

    public void goToEmployee() {
        this.dispose();
        new Employee();
    }

    public void goToAdmin() {
        this.dispose();
        new Admin();
    }

    public static void main(String[] args) {
        Database.connect();
        setupClosingDBConnection();
        new MainMenu();

    }

    public static void setupClosingDBConnection() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    Database.connection.close();
                    System.out.println("Application Closed - DB Connection Closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, "Shutdown-thread"));
    }
}
