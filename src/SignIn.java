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
//        private void handleLogin() {
//            String usernameText = username.getText().trim();
//            String passwordText = new String(password.getPassword());
//
//            if (usernameText.isEmpty() || passwordText.isEmpty()) {
//                JOptionPane.showMessageDialog(this,
//                        "Please enter both username and password.");
//                return;
//            }
//
//            try {
//                String query = "SELECT user_id, password_hash, role FROM users WHERE username = ?";
//                PreparedStatement stm = Database.connection.prepareStatement(query);
//
//                // set the ? in the query
//                stm.setString(1, usernameText);
//
//                ResultSet rs = stm.executeQuery();
//
//                if (!rs.next()) {
//                    // no such username
//                    JOptionPane.showMessageDialog(this,
//                            "Invalid username or password.");
//                    return;
//                }
//
//                int userId = rs.getInt("user_id");
//                String storedPassword = rs.getString("password_hash");
//                String role = rs.getString("role");
//
//                // for now, plain compare – later use EncryptionManager if you want
//                if (!storedPassword.equals(passwordText)) {
//                    JOptionPane.showMessageDialog(this,
//                            "Invalid username or password.");
//                    return;
//                }
//
//                // correct username + password → route by role
//                if ("CUSTOMER".equalsIgnoreCase(role)) {
//                    JOptionPane.showMessageDialog(this, "Logged in as CUSTOMER (id " + userId + ")");
//                    // new CustomerDashboard(...);
//                } else if ("EMPLOYEE".equalsIgnoreCase(role)) {
//                    JOptionPane.showMessageDialog(this, "Logged in as EMPLOYEE (id " + userId + ")");
//                    // new EmployeeDashboard(...);
//                } else if ("ADMIN".equalsIgnoreCase(role)) {
//                    JOptionPane.showMessageDialog(this, "Logged in as ADMIN (id " + userId + ")");
//                    // new AdminDashboard(...);
//                }

//                this.dispose();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(this,
//                        "Database error: " + e.getMessage());
//            }
//        }

    }
}
