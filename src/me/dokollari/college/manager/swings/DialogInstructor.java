package me.dokollari.college.manager.swings;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import me.dokollari.college.manager.models.Instructor;
import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.mvc.ControllerException;

/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogInstructor extends JDialog {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JTextField lastnameTF = new JTextField();
    private JTextField firstnameTF = new JTextField();
    private JSeparator jSeparator1 = new JSeparator();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTA_data = new JTextArea();
    private JButton jButton1 = new JButton();
    private Controller controller = new Controller();
    private JTextField jTFid = new JTextField();
    private JLabel jLabel3 = new JLabel();
    private JButton jButton2 = new JButton();
    private JTextField jTFidRemove = new JTextField();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();


    public DialogInstructor(Frame parent, String title, boolean modal, Controller controller) {
        super(parent, title, modal);
        try {
            this.controller = controller;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setResizable(false);
        this.setSize(new Dimension(788, 313));
        this.getContentPane().setLayout(null);
        jLabel1.setText("Last Name");
        jLabel1.setBounds(new Rectangle(20, 45, 70, 30));
        jLabel2.setText("First Name");
        jLabel2.setBounds(new Rectangle(20, 85, 70, 20));
        lastnameTF.setBounds(new Rectangle(90, 50, 170, 25));
        firstnameTF.setBounds(new Rectangle(90, 85, 170, 25));
        jSeparator1.setBounds(new Rectangle(30, 250, 710, 10));
        jScrollPane1.setBounds(new Rectangle(285, 50, 330, 190));
        jButton1.setText("Register");
        jButton1.setBounds(new Rectangle(20, 180, 85, 25));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jTFid.setBounds(new Rectangle(90, 125, 110, 25));
        jLabel3.setText("ID");
        jLabel3.setBounds(new Rectangle(20, 125, 70, 20));
        jButton2.setText("Remove");
        jButton2.setBounds(new Rectangle(650, 120, 110, 25));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jTFidRemove.setBounds(new Rectangle(650, 75, 110, 25));
        jLabel4.setText("Instructors On Database");
        jLabel4.setBounds(new Rectangle(285, 30, 210, 15));
        jLabel5.setText("Enter ID");
        jLabel5.setBounds(new Rectangle(650, 40, 110, 25));
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jTFidRemove, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jTFid, null);
        this.getContentPane().add(jButton1, null);
        jScrollPane1.getViewport().add(jTA_data, null);
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(jSeparator1, null);
        this.getContentPane().add(firstnameTF, null);
        this.getContentPane().add(lastnameTF, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        jTA_data.setFont(new Font("Courier New", Font.PLAIN, 12));  
        jTA_data.setText(controller.listInstructorData(controller.getInstructorsList()));
        jTA_data.setEditable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void jButton1_actionPerformed(ActionEvent e) {
        try {

            controller.verifyFacultyData(lastnameTF.getText(), firstnameTF.getText(), jTFid.getText(), "Instructor");
            Integer instructor_id = Integer.parseInt(jTFid.getText());
            Instructor new_instructor = new Instructor(lastnameTF.getText(), firstnameTF.getText(), instructor_id);
            if (!controller.addData(instructor_id, new_instructor,  controller.getInstructorsList()))
                ;
            {
                jTFid.setText("");
                lastnameTF.setText("");
                firstnameTF.setText("");
                jTA_data.setText(controller.listInstructorData(controller.getInstructorsList()));
            }
        } catch (ControllerException k) {
            JOptionPane.showMessageDialog(null, k.getMessage(), "Data error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        String input = jTFidRemove.getText();
        try {
            controller.verifyNumber(input, "Instructor ID");
            int ID = Integer.parseInt(input);
            if (controller.getInstructorsList().containsKey(ID)){
                controller.getInstructorsList().remove(ID);
                jTA_data.setText(controller.listInstructorData(controller.getInstructorsList()));
            }
            else
                throw new ControllerException("ID not found");
        } catch (ControllerException k) {
            JOptionPane.showMessageDialog(null, k.getMessage(), "Data Error", JOptionPane.WARNING_MESSAGE);
        }


    }
}
