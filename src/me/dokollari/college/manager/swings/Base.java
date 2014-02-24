package me.dokollari.college.manager.swings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


 /** @author Rizart Dokollari
 * @since November, 2012
 */

abstract class Base extends JDialog implements ActionListener {
    protected JPanel facultyPanel = new JPanel();
    protected JPanel buttonPanel = new JPanel();
    private JLabel jLb_id = new JLabel("Enter ID:");
    protected JTextField jTF_ID = new JTextField(6);
    JLabel jLbstudentLastName = new JLabel("Last Name");
    JTextField jTFStudentLastName = new JTextField(10);
    JLabel jLbstudentFirstName = new JLabel("First Name");
    JTextField jTFstudentFirstName = new JTextField(10);
    protected JButton jBtn_ok = new JButton("Confirm");
    protected JButton jBtn_cancel = new JButton("Back");
    private JLabel jL_image = new JLabel();

    public Base(JDialog parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setResizable(false);
        facultyPanel.add(jLb_id);
        facultyPanel.add(jTF_ID);

        jL_image.setIcon(new ImageIcon("button_deree.jpg"));
        buttonPanel.add(jL_image);
        buttonPanel.add(jBtn_ok);
        buttonPanel.add(jBtn_cancel);
        jBtn_cancel.addActionListener(new CancelButtonListener());
        jBtn_ok.addActionListener(this);
        buttonPanel.setBorder(new TitledBorder("Actions"));
        this.setLocationRelativeTo(null);

    }

   

    private class CancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
      
            setVisible(false);

        }
    }
}
