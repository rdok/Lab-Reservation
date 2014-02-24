package me.dokollari.college.manager.swings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import me.dokollari.college.manager.mvc.Controller;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogFacultyMain extends JDialog {
    //LabReservation frame1 = new LabReservation();
    
   
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    Controller theCollege = new Controller();

    public DialogFacultyMain(Frame parent, String title, boolean modal, Controller theCollege) {
        super(parent, title, modal);
        try {
            this.theCollege = theCollege;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize( new Dimension(400, 300) );
        this.setTitle( "College Registration" );
        this.setBackground(new Color(115, 115, 115));
        jButton1.setText("Instructor Data");
        jButton1.setBounds(new Rectangle(139, 25, 120, 35));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
        jButton2.setText("Course Data");
        jButton2.setBounds(new Rectangle(139, 75, 120, 35));
        jButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton2_actionPerformed(e);
                }
            });
        jButton3.setText("Student Data");
        jButton3.setBounds(new Rectangle(139, 130, 120, 35));
        jButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton3_actionPerformed(e);
                }
            });
        jButton4.setText("Register Students to Courses");
        jButton4.setBounds(new Rectangle(104, 185, 190, 35));
        jButton4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton4_actionPerformed(e);
                }
            });
        jButton5.setText("Back");
        jButton5.setBounds(new Rectangle(20, 205, 75, 20));
        jButton5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton5_actionPerformed(e);
                }
            });
        
        this.getContentPane().add(jButton5, null);
        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton1, null);
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
    }

    

   

    private void jButton1_actionPerformed(ActionEvent e) {
        new me.dokollari.college.manager.swings.DialogInstructor(null, "College Instructors" , true, theCollege);
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        new me.dokollari.college.manager.swings.DialogCourse(null, "College Courses" , true, theCollege);
    }

    private void jButton3_actionPerformed(ActionEvent e) {
        new me.dokollari.college.manager.swings.DialogStudentData(null, "College Students" , true, theCollege);
    }

    private void jButton4_actionPerformed(ActionEvent e) {
        new me.dokollari.college.manager.swings.DialogStudentRegisterCourse(null, "Data - Register Student To Courses", true, theCollege);
    }
    
  
    private void jButton5_actionPerformed(ActionEvent e) {
         
        this.setVisible(false);
    }
}
