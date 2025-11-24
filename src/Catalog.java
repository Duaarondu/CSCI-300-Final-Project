import javax.swing.*;

public class Catalog extends JFrame {
    private JComboBox comboBox1;
    private JButton cartButton;
    private JButton myOrderButton;
    private JPanel panel;

    public Catalog() {

            this.setContentPane(this.panel);
            this.setTitle("Catalog");
            this.setBounds(600, 200, 300, 300);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


