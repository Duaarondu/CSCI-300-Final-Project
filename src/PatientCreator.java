import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class PatientCreator extends JFrame {
    private JPanel panel;
    private JTextField patientIDTF;
    private JTextField patientNameTF;
    private JTextField dateOfBirthTF;
    private JButton createPatientButton;

    public PatientCreator() {
    this.setContentPane(this.panel);
    this.setTitle("Patient Creator");
    this.setBounds(600, 200, 300, 300);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     setUpCreatePatientButton();
    }
    public void setUpCreatePatientButton() {
        createPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPatient();
            }
        });
    }

    public void createPatient() {
        try {
            String query = "INSERT INTO Patients VALUES (?, ?, ?)";
            PreparedStatement stm = Database.connection.prepareStatement(query);

            stm.setInt(1, Integer.parseInt(patientIDTF.getText()));
            stm.setString(2, patientNameTF.getText());
            stm.setString(3, dateOfBirthTF.getText());
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "The new patient was added to the database!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            throw new RuntimeException(e);
        }
    }



}