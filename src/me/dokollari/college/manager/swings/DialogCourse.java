package me.dokollari.college.manager.swings;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import me.dokollari.college.manager.models.Course;
import me.dokollari.college.manager.models.Instructor;
import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.mvc.ControllerException;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogCourse extends JDialog {

    private JTextField jTextField1 = new JTextField();
    private JLabel jLabel1 = new JLabel();
    private JTextField jTFCourseTitle = new JTextField();
    private JComboBox jCBInstruct = new JComboBox();
    private JButton jButton1 = new JButton();
    private Controller controller = new Controller();
    private JButton jButton2 = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTAListCourses = new JTextArea();
    private JLabel jLabel2 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JLabel jLabel3 = new JLabel();
    private JTextField jTFsLimit = new JTextField();


    public DialogCourse(Frame parent, String title, boolean modal, Controller controller) {
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
        this.setSize(new Dimension(355, 485));
        this.getContentPane().setLayout(null);
        jLabel1.setText("Title");
        jLabel1.setBounds(new Rectangle(20, 40, 85, 20));
        jTFCourseTitle.setBounds(new Rectangle(120, 40, 195, 20));
        jTFCourseTitle.setFont(new Font("Times New Roman", 0, 12));
        jCBInstruct.setBounds(new Rectangle(20, 130, 295, 25));
        jCBInstruct.setFont(new Font("Times New Roman", 0, 12));
        jButton1.setText("Add");
        jButton1.setBounds(new Rectangle(185, 195, 130, 25));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jButton2.setText("Back");
        jButton2.setBounds(new Rectangle(35, 195, 130, 25));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jScrollPane1.setBounds(new Rectangle(15, 255, 310, 180));
        this.getContentPane().add(jTFsLimit, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jSeparator1, null);
        this.getContentPane().add(jLabel2, null);
        jScrollPane1.getViewport().add(jTAListCourses, null);
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jCBInstruct, null);
        this.getContentPane().add(jTFCourseTitle, null);
        this.getContentPane().add(jLabel1, null);
        this.setLocationRelativeTo(null);
        addComboBoxes(controller);
        jTAListCourses.setText(controller.listData(controller.getCoursesList()));
        jTAListCourses.setFont(new Font("Courier New", Font.PLAIN, 12));  
        jTAListCourses.setEditable(false);
        jLabel2.setText("List of Courses");
        jLabel2.setBounds(new Rectangle(15, 240, 150, 15));
        jSeparator1.setBounds(new Rectangle(5, 235, 330, 5));
        jLabel3.setText("Student Limit");
        jLabel3.setBounds(new Rectangle(20, 80, 85, 20));
        jTFsLimit.setBounds(new Rectangle(120, 80, 70, 20));
        this.setVisible(true);
    }

    private void jButton1_actionPerformed(ActionEvent e) {
        try {
            String studentsLimit = jTFsLimit.getText();
            controller.verifyTitle(jTFCourseTitle.getText());
            controller.verifyNumber(studentsLimit, "Student Limit");
            Instructor an_instructor = (Instructor) jCBInstruct.getSelectedItem();
            if (controller.addData(controller.getCoursesList(),
                                    new Course(jTFCourseTitle.getText(), Integer.parseInt(studentsLimit),
                                               an_instructor.getInstructorID()))) {
                jTFCourseTitle.setText("");
                jTAListCourses.setText(controller.listData(controller.getCoursesList()));
            }
        } catch (ControllerException k) {
            JOptionPane.showMessageDialog(null, k.getMessage(), "Data Error", JOptionPane.WARNING_MESSAGE);
        } catch (ClassCastException k) {
            JOptionPane.showMessageDialog(null,
                                          "You must also select an instructor for the class you wish to register.",
                                          "Missing Data", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void addComboBoxes(Controller controller) {
        //add Elements to jCombox1
        jCBInstruct.addItem("Select Instructor");
        for (Map.Entry<Integer, Instructor> e : controller.getInstructorsList().entrySet())
            jCBInstruct.addItem(e.getValue());

    }

    private void jComboBox2_actionPerformed(ActionEvent e) {
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    private void jComboBox3_actionPerformed(ActionEvent e) {
    }
}
