import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CouponCreator extends JFrame {
    private JPanel panel;
    private JTextField codeField;
    private JTextField descriptionField;
    private JTextField percentField;
    private JButton createButton;
    private JLabel couponNumberLabel;
    private JLabel couponDescriptionLabel;
    private JLabel couponPercentLabel;

    public CouponCreator() {
        this.setContentPane(panel);
        this.setTitle("Create Coupon");
        this.setBounds(650, 250, 360, 220);
        this.setVisible(true);
        couponNumberLabel.setText("Coupon Code");
        couponDescriptionLabel.setText("Coupon Description");
        couponPercentLabel.setText("Coupon Discount");

        createButton.addActionListener(e -> createCoupon());
    }

    private void createCoupon() {
        String code = codeField.getText().trim();
        String desc = descriptionField.getText().trim();
        String pct = percentField.getText().trim();

        if (code.isEmpty() || pct.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Code and discount required.");
            return;
        }

        try {
            String sql = "INSERT INTO coupons (code, description, discount_percent, active) VALUES (?, ?, ?, true)";
            PreparedStatement ps = Database.connection.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, desc);
            ps.setInt(3, Integer.parseInt(pct));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Coupon created!");
            codeField.setText("");
            descriptionField.setText("");
            percentField.setText("");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Percent must be a number.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
