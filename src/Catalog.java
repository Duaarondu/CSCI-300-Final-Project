import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Catalog extends JFrame {
    private JButton cartButton;
    private JButton myOrderButton;
    private JPanel panel;
    private JTable table;

    public Catalog() {

            this.setContentPane(this.panel);
            this.setTitle("Catalog");
            this.setBounds(600, 200, 300, 300);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SetUpCartButton();
            SetUpOrderButton();


    }

    public void SetUpCartButton() {
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void SetUpOrderButton() {
        myOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}


