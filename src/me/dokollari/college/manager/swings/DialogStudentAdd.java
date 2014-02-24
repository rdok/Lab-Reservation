package me.dokollari.college.manager.swings;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import me.dokollari.college.manager.models.Student;
import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.mvc.ControllerException;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogStudentAdd extends me.dokollari.college.manager.swings.Base {

    JPanel studentDetails = new JPanel();
    private Controller controller = new Controller();

    public DialogStudentAdd(JDialog parent, Controller theCollege) {
        super(parent, "Register Student", true);
        this.controller = theCollege;
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        facultyPanel.add(jLbstudentFirstName);
        facultyPanel.add(jTFstudentFirstName);
        facultyPanel.add(jLbstudentLastName);
        facultyPanel.add(jTFStudentLastName);
        getContentPane().add(facultyPanel);
        getContentPane().add(studentDetails);
        getContentPane().add(buttonPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Student theStudent;
        try {
            controller.verifyFacultyData(jTFStudentLastName.getText(), jTFstudentFirstName.getText(),
                                           jTF_ID.getText(), "Student");
            Integer student_id = Integer.parseInt(jTF_ID.getText());
            theStudent = new Student(student_id, jTFStudentLastName.getText(), jTFstudentFirstName.getText());
            if (controller.addData(student_id, theStudent, controller.getStudentsList())) {
                jTFStudentLastName.setText("");
                jTFstudentFirstName.setText("");
                jTF_ID.setText("");
            }
        } catch (ControllerException d) {
            JOptionPane.showMessageDialog(null, d.getMessage(), "Data  Error", JOptionPane.WARNING_MESSAGE);
        }
        
    }
   
}
