package me.dokollari.college.manager.swings;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import me.dokollari.college.manager.mvc.ControllerException;
import me.dokollari.college.manager.mvc.Controller;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogStudentRemove extends me.dokollari.college.manager.swings.Base {
    JPanel studentDetails = new JPanel();
    private Controller controller = new Controller();

    public DialogStudentRemove(JDialog parent, Controller theCollege) {
        super(parent, "Remove Student", true);
        this.controller = theCollege;
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(facultyPanel);
        getContentPane().add(studentDetails);
        getContentPane().add(buttonPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (controller.removeData(Integer.parseInt(jTF_ID.getText()), controller.getStudentsList()))
                JOptionPane.showMessageDialog(null, "Student Removed");
        } catch (ControllerException d) {
            JOptionPane.showMessageDialog(null, d.getMessage(), "Data  Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
