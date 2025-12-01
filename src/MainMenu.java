import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainMenu extends JDialog {
    private JPanel contentPane;
    private JButton signInButton;
    private JButton registerButton;

    public MainMenu() {
        this.setContentPane(contentPane);
        this.setTitle("Main Menu");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUpSignInButtonAction();
        setUpRegisterButtonAction();

    }
    public void setUpSignInButtonAction() {
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToSignIn();
            }
        });
    }

    public void setUpRegisterButtonAction() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToRegister();
            }
        });
    }


    public void goToSignIn() {
        this.dispose();
        new SignIn();
    }

    public void goToRegister() {
        this.dispose();
        new Register();
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
