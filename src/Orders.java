import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Orders extends JFrame {
    private JButton cancelOrderButton;
    private JButton submitButton;
    private JPanel panel;
    private JButton backButton;

    public Orders() {
        this.setContentPane(this.panel);
        this.setTitle("Cart");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setupCancelOrderButton();
        this.setUpSubmitButton();
        this.SetUpBackButton();


    }

    public void setupCancelOrderButton() {
        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setUpSubmitButton(){
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void SetUpBackButton() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Catalog().setVisible(true);

            }
        });

    }


}


