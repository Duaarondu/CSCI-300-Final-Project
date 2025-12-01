import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    }

//    public itemList() {
//        this.setContentPane(this.panel);
//        this.setTitle("Table Demo");
//        this.setBounds(600, 200, 300, 300);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        this.populateTable();
//    }
//
//    public void populateTable() {
//        try {
//            String query = "SELECT Appointments.appointment_time, Patients.patient_name, Doctors.doctor_name " +
//                    "FROM Appointments " +
//                    "JOIN Patients " +
//                    "ON Appointments.patient_id = Patients.patient_id " +
//                    "JOIN Doctors " +
//                    "ON Appointments.doctor_id = Doctors.doctor_id";
//            PreparedStatement stm = Database.connection.prepareStatement(query);
//            ResultSet result = stm.executeQuery(query);
//            table1.setModel(DbUtils.resultSetToTableModel(result));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

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


