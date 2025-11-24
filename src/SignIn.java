import javax.swing.*;

public class SignIn extends JFrame{
    private JPanel panel;
    private JTextField username;
    private JPasswordField password;
    private JButton signIn;


    public SignIn() {
    this.setContentPane(this.panel);
    this.setTitle("Sign In");
    this.setBounds(600, 200, 300, 300);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        signIn.addActionListener(e -> Login());

        this.setVisible(true);

}
    private void Login() {
        String usernameText = username.getText().trim();
        String passwordText = new String(password.getPassword());

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Missing info",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //User u = Database.findUserbyUsername(usernameText);  username when database is done

//        if (u == null || !u.checkPassword(passwordText)) {
//            JOptionPane.showMessageDialog(this,
//                    "Invalid username or password.");
//            return;
//        }
//
//        if (u instanceof Customer) {
//            JOptionPane.showMessageDialog(this, "Logged in as CUSTOMER");
//        } else if (u instanceof Employee) {
//            JOptionPane.showMessageDialog(this, "Logged in as EMPLOYEE");
//        } else if (u instanceof Admin) {
//            JOptionPane.showMessageDialog(this, "Logged in as ADMIN");
//        }
//
//        this.dispose();
    }
}
