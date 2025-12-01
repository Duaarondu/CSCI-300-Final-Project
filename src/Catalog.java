import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Catalog extends JFrame {
    private JButton cartButton;
    private JButton myOrderButton;
    private JPanel panel;
    private JTable table;
    private JButton backButton;
    private JButton ADDButton;

    public Catalog() {

        this.setContentPane(this.panel);
        this.setTitle("Catalog");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SetUpCartButton();
        SetUpOrderButton();
        SetUpBackButton();
        this.itemList();
        this.setUpAddButton();



    }

    public void itemList() {
        this.setContentPane(this.panel);
        this.setTitle("Thrift Store");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.populateTable();
    }

    public void populateTable() {
        try {
            String query = "SELECT item_id,name, price, quantity_in_stock FROM items ";
            PreparedStatement stm = Database.connection.prepareStatement(query);
            ResultSet result = stm.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SetUpCartButton() {
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToCart();


            }
        });

    }

    public void goToCart(){
        this.dispose();
        new Cart();
    }

    public void SetUpOrderButton() {
        myOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToOrders();

            }
        });
    }

    public void goToOrders() {
        this.dispose();
        new Orders();
    }

    public void setUpAddButton(){   //this will add any items chosen by user and sent into the cart page
        ADDButton.addActionListener(new ActionListener() {
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
                new SignIn().setVisible(true);

            }
        });

    }

}


