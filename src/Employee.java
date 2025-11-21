import javax.swing.*;

public class Employee extends JFrame {
    private JPanel contentPane;

    private JPanel panel;

    public Employee() {
        this.setContentPane(this.panel);
        this.setTitle("Employee");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

