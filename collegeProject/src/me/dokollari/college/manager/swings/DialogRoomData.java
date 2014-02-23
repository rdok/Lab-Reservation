package me.dokollari.college.manager.swings;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import me.dokollari.college.manager.mvc.ControllerException;
import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.models.Room;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogRoomData extends JDialog {
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTAlistRooms = new JTextArea();
    Controller controller = new Controller();
    int currNumRoom = 0;
    private JButton jButton3 = new JButton();
    private JButton jButton2 = new JButton();
    private JButton jButton4 = new JButton();
    private JTextField jTFinputCreateRoom = new JTextField();
    private JTextField jTFinputRemove = new JTextField();

    public DialogRoomData(Frame parent, String title, boolean modal, Controller theCollege) {
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
        this.setSize(new Dimension(513, 303));
        this.getContentPane().setLayout(null);
        jScrollPane1.setBounds(new Rectangle(190, 30, 275, 165));
        jButton3.setText("Back");
        jButton3.setBounds(new Rectangle(35, 230, 75, 21));
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }
        });
        jButton2.setText("Create Room");
        jButton2.setBounds(new Rectangle(35, 70, 125, 20));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jButton4.setText("Remove Room");
        jButton4.setBounds(new Rectangle(35, 135, 125, 20));
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton4_actionPerformed(e);
            }
        });
        jTFinputCreateRoom.setBounds(new Rectangle(35, 40, 125, 20));
        jTFinputRemove.setBounds(new Rectangle(35, 100, 125, 20));
        this.getContentPane().add(jTFinputRemove, null);
        this.getContentPane().add(jTFinputCreateRoom, null);
        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton3, null);
        jScrollPane1.getViewport().add(jTAlistRooms, null);
        this.getContentPane().add(jScrollPane1, null);
        jTAlistRooms.setEditable(false);
        this.setLocationRelativeTo(null);
        fillMain();
        jTAlistRooms.setFont(new Font("Times New Roman", 0, 12));
        this.setVisible(true);

    }


    private void jButton3_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }


    public void fillMain() {
        jTAlistRooms.setText("---Current Maximum Number of Rooms " + controller.getRoomList().size() + "---\n" +
                controller.listData(controller.getRoomList()));
    }


    private void jButton2_actionPerformed(ActionEvent e) {
        Room regisNewRoom;
        try {
            controller.verifyRoomTitle(jTFinputCreateRoom.getText());
            regisNewRoom = new Room(jTFinputCreateRoom.getText());
            if (controller.addData(controller.getRoomList(), regisNewRoom)) {
                jTFinputCreateRoom.setText("");
                JOptionPane.showMessageDialog(null, "Room Created");
                fillMain();
                jTFinputCreateRoom.setText("");
            }
        } catch (ControllerException k) {
            JOptionPane.showMessageDialog(null, k.getMessage(), "Data Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void jButton4_actionPerformed(ActionEvent e) {
        try {
            controller.verifyRoomTitle(jTFinputRemove.getText());
            if (!controller.getRoomList().remove(new Room(jTFinputRemove.getText())))
                JOptionPane.showMessageDialog(null, "Room Not Found On Database", "Error",
                                              JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Room Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
            fillMain();
            jTFinputRemove.setText("");
        } catch (ControllerException k) {
            JOptionPane.showMessageDialog(null, k.getMessage(), "Syntax Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
