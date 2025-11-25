import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn extends JFrame {
    private JPanel panel;
    private JTextField username;
    private JPasswordField password;
    private JButton signIn;

    public SignIn() {
        this.setContentPane(this.panel);
        this.setTitle("Sign In");
        this.setBounds(600, 200, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ONE listener only
        signIn.addActionListener(e -> handleLogin());

        this.setVisible(true);
    }

    private void handleLogin() {
        if (Database.connection == null) {
            JOptionPane.showMessageDialog(this,
                    "No database connection.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String usernameText = username.getText().trim();
        String passwordText = new String(password.getPassword());

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Missing info",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 1️⃣ Try employees
            if (checkLoginAndOpenDashboard(
                    "employees",
                    usernameText,
                    passwordText,
                    "employee"
            )) return;

            // 2️⃣ Try customers
            if (checkLoginAndOpenDashboard(
                    "customers",
                    usernameText,
                    passwordText,
                    "customer"
            )) return;

            // 3️⃣ Try admins
            if (checkLoginAndOpenDashboard(
                    "admins",
                    usernameText,
                    passwordText,
                    "admin"
            )) return;

            // If all 3 failed:
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Returns true if login succeeded for this table and opens the right dashboard.
     */
    private boolean checkLoginAndOpenDashboard(String tableName,
                                               String usernameText,
                                               String passwordText,
                                               String roleType) throws SQLException {

        String sql = "SELECT password_hash, full_name FROM " + tableName + " WHERE username = ?";

        try (PreparedStatement stm = Database.connection.prepareStatement(sql)) {
            stm.setString(1, usernameText);

            try (ResultSet rs = stm.executeQuery()) {
                if (!rs.next()) {
                    // no such username in this table → not a match, keep trying others
                    return false;
                }

                String storedPassword = rs.getString("password_hash");
                String fullName = rs.getString("full_name");

                if (!passwordText.equals(storedPassword)) {
                    // username exists here but password wrong → treat as failure
                    return false;
                }

                // ✅ Successful login for this role
                JOptionPane.showMessageDialog(this,
                        "Login successful! Welcome, " + fullName + " (" + roleType + ").");

                switch (roleType) {
                    case "employee":
                        openEmployeeMenu(fullName, usernameText);
                        break;
                    case "customer":
                        openCustomerMenu(fullName, usernameText);
                        break;
                    case "admin":
                        openAdminMenu(fullName, usernameText);
                        break;
                }

                return true;
            }
        }
    }

    // 🔽 Dashboard navigation methods – adjust constructors to match your classes

    private void openEmployeeMenu(String fullName, String username) {
        this.dispose();
        // If your EmployeeMenu has no args, just do: new EmployeeMenu();
        new EmployeeMenu();
    }

    private void openCustomerMenu(String fullName, String username) {
        this.dispose();
        new CustomerMenu();
    }

    private void openAdminMenu(String fullName, String username) {
        this.dispose();
        new AdminMenu();
    }
}
