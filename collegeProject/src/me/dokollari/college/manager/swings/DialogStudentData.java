package me.dokollari.college.manager.swings;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import me.dokollari.college.manager.mvc.Controller;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogStudentData extends JDialog {

    private Controller controller = new Controller();
    private JSeparator jSeparator1 = new JSeparator();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTAlistStudents = new JTextArea();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JTextArea jTextArea1 = new JTextArea();

    public DialogStudentData(Frame parent, String title, boolean modal, Controller theCollege) {
        super(parent, title, modal);
        try {
            this.controller = theCollege;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setResizable(false);
        this.setSize(new Dimension(390, 403));
        this.getContentPane().setLayout(null);
        jSeparator1.setBounds(new Rectangle(15, 105, 345, 10));
        jScrollPane1.setBounds(new Rectangle(25, 130, 330, 210));
        jButton2.setText("Back");
        jButton2.setBounds(new Rectangle(130, 65, 120, 20));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jButton3.setText("Register Student");
        jButton3.setBounds(new Rectangle(125, 25, 135, 20));
        jButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton3_actionPerformed(e);
                }
            });
        jButton4.setText("Refresh");
        jButton4.setBounds(new Rectangle(285, 85, 80, 15));
        jButton4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton4_actionPerformed(e);
                }
            });
        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jButton2, null);
        jScrollPane1.getViewport().add(jTextArea1, null);
        this.getContentPane().add(jScrollPane1, null);
        jScrollPane1.getViewport().add(this.jTAlistStudents, null);
        this.getContentPane().add(jSeparator1, null);
        this.jTAlistStudents.setText(controller.listStudentData(controller.getStudentsList()));
        this.jTAlistStudents.setFont(new Font("Times New Roman", 0, 12));
        this.jTAlistStudents.setEditable(false);
        this.setLocationRelativeTo(null);
        jTAlistStudents.setText(controller.listStudentData(controller.getStudentsList()));
        this.setVisible(true);
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }


    private void jButton3_actionPerformed(ActionEvent e) {
        new me.dokollari.college.manager.swings.DialogStudentAdd(this, controller);
    }

    private void jButton4_actionPerformed(ActionEvent e) {
        jTAlistStudents.setText(controller.listStudentData(controller.getStudentsList()));

    }
  
}
