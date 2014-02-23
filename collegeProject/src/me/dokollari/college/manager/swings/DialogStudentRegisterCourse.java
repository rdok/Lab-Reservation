package me.dokollari.college.manager.swings;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import me.dokollari.college.manager.models.Course;
import me.dokollari.college.manager.models.Student;
import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.mvc.ControllerException;

/** @author Rizart Dokollari
 * @since November, 2012
 */


public class DialogStudentRegisterCourse extends JDialog {
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTAstudentList = new JTextArea();
    private JLabel jLabel1 = new JLabel();
    private JComboBox jCBselectStudent = new JComboBox();
    private JLabel jLabel2 = new JLabel();
    Controller controller = new Controller();
    private JComboBox jCBselectCourse = new JComboBox();
    private JLabel jLabel4 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();


    public DialogStudentRegisterCourse(Frame parent, String title, boolean modal, Controller controller) {
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
        this.setSize(new Dimension(460, 440));
        this.getContentPane().setLayout(null);
        jScrollPane1.setBounds(new Rectangle(35, 135, 380, 160));
        jLabel1.setText("Enter ID");
        jLabel1.setBounds(new Rectangle(35, 35, 105, 20));
        jCBselectStudent.setBounds(new Rectangle(175, 30, 230, 20));
        jCBselectStudent.setFont(new Font("Times New Roman", 0, 12));
        jLabel2.setText("Students List");
        jLabel2.setBounds(new Rectangle(35, 110, 80, 15));
        jCBselectCourse.setBounds(new Rectangle(175, 65, 230, 20));
        jCBselectCourse.setFont(new Font("Times New Roman", 0, 12));
        jCBselectCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCBselectCourse_actionPerformed(e);
            }
        });
        jLabel4.setText("Select Course");
        jLabel4.setBounds(new Rectangle(35, 65, 80, 20));

        jSeparator1.setBounds(new Rectangle(0, 95, 465, 5));
        jButton1.setText("Register");
        jButton1.setBounds(new Rectangle(290, 335, 125, 35));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jButton2.setText("Back");
        jButton2.setBounds(new Rectangle(35, 340, 125, 35));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });

        fillComboBoxes();


        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jSeparator1, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jCBselectCourse, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jCBselectStudent, null);
        this.getContentPane().add(jLabel1, null);
        jScrollPane1.getViewport().add(jTAstudentList, null);
        this.getContentPane().add(jScrollPane1, null);
        jTAstudentList.setFont(new Font("Times New Roman", 0, 12));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void fillComboBoxes() {
        Iterator i = controller.getCoursesList().iterator();
        while (i.hasNext())
            jCBselectCourse.addItem(i.next());

        jCBselectStudent.addItem("Or Select a Student from this List");
        for(Map.Entry<Integer, Student> e: controller.getStudentsList().entrySet())   
            jCBselectStudent.addItem(e.getValue());
    }

    private void jCBselectCourse_actionPerformed(ActionEvent e) {
        Course tempCourse;
        tempCourse = (Course) jCBselectCourse.getSelectedItem();
        jTAstudentList.setText(tempCourse.displayClasslist());
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    private void jButton1_actionPerformed(ActionEvent e) {

        Course selectedCourse = (Course) jCBselectCourse.getSelectedItem();
        Student selectedStudent;
        try {
            selectedStudent = (Student) jCBselectStudent.getSelectedItem();
            if (selectedCourse.registerStudent(selectedStudent.getStudent_id(), selectedStudent))
                jTAstudentList.setText(selectedCourse.displayClasslist());
        } catch(ControllerException k){
            JOptionPane.showMessageDialog(null, k.getMessage(), "Data Error", JOptionPane.WARNING_MESSAGE);
        }catch (ClassCastException p) {
            JOptionPane.showMessageDialog(null,
                                         "Please, either enter an ID or select a student from the list of students",
                                         "User data missing", JOptionPane.WARNING_MESSAGE);

        }
    }
}