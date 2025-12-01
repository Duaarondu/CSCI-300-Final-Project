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

    }

    public void itemList() {
        this.setContentPane(this.panel);
        this.setTitle("Table Demo");
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.populateTable();
    }

    public void populateTable() {
        try {
            String query = "SELECT items.item_id, items.name, items.price, items.quantity_in_stock ";
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


