package me.dokollari.college.manager.swings;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import me.dokollari.college.manager.models.Course;
import me.dokollari.college.manager.models.Room;
import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.mvc.ControllerException;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class DialogRoomReservationsData extends JDialog {
    @SuppressWarnings("compatibility:-6960348388823336134")
    private static final long serialVersionUID = -2060570395006911172L; //per day

    private JComboBox jCBcourseList = new JComboBox();
    private JComboBox jCBselectedDate = new JComboBox();
    private JComboBox jCBRooms = new JComboBox();
    private JButton jButton1 = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private Controller controller = new Controller();
    private JButton jButton2 = new JButton();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton6 = new JButton();
    private JButton jButton7 = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTAMMessageMenu = new JTextArea();
    private JSeparator jSeparator1 = new JSeparator();
    private JLabel jLabel4 = new JLabel();

    public DialogRoomReservationsData(Frame parent, String title, boolean modal, Controller theCollege) {
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
        this.setSize(new Dimension(896, 386));
        this.getContentPane().setLayout(null);
        jCBcourseList.setBounds(new Rectangle(25, 90, 215, 20));
        jCBcourseList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jComboBox1_actionPerformed(e);
            }
        });
        jCBselectedDate.setBounds(new Rectangle(25, 200, 215, 20));
        jCBselectedDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCBselectedDate_actionPerformed(e);
            }
        });
        jCBRooms.setBounds(new Rectangle(25, 145, 215, 20));
        jCBRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCBRooms_actionPerformed(e);
            }
        });
        jButton1.setText("Confirm Reservation");
        jButton1.setBounds(new Rectangle(275, 30, 175, 40));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jLabel1.setText("Courses");
        jLabel1.setBounds(new Rectangle(25, 70, 130, 20));
        jLabel2.setText("Date");
        jLabel2.setBounds(new Rectangle(25, 185, 140, 15));
        jLabel3.setText("Room");
        jLabel3.setBounds(new Rectangle(25, 130, 140, 15));
        jButton2.setText("Back");
        jButton2.setBounds(new Rectangle(25, 265, 90, 30));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jButton4.setText("Remove Reservation");
        jButton4.setBounds(new Rectangle(480, 30, 175, 40));
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton4_actionPerformed(e);
            }
        });
        jButton5.setText("Exit");
        jButton5.setBounds(new Rectangle(145, 265, 90, 30));
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton5_actionPerformed(e);
            }
        });
        jButton3.setText("Display All Lab Reservations");
        jButton3.setBounds(new Rectangle(675, 85, 185, 40));
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }
        });
        jButton6.setText("Display Reservations for Today");
        jButton6.setBounds(new Rectangle(675, 160, 185, 40));
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton6_actionPerformed(e);
            }
        });
        jButton7.setText("Display Today's Empty Labs");
        jButton7.setBounds(new Rectangle(675, 240, 185, 40));
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton7_actionPerformed(e);
            }
        });
        jScrollPane1.setBounds(new Rectangle(270, 85, 390, 220));
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jSeparator1, null);
        jScrollPane1.getViewport().add(jTAMMessageMenu, null);
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(jButton7, null);
        this.getContentPane().add(jButton6, null);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jButton5, null);
        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jCBRooms, null);
        this.getContentPane().add(jCBselectedDate, null);
        this.getContentPane().add(jCBcourseList, null);
        jSeparator1.setBounds(new Rectangle(15, 55, 245, 10));
        jLabel4.setText("Requirements for Reservation/Cancel Reservation");
        jLabel4.setBounds(new Rectangle(15, 15, 260, 30));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        fillComboBoxes();
        displayTodaysEmptyRooms();
        jTAMMessageMenu.setEditable(false);
        this.setVisible(true);
    }

    private void fillComboBoxes() {
        Calendar todaysCalendar = (Calendar)Calendar.getInstance().clone();
        /*
         *
         */
        jCBcourseList.addItem("Select Course");
        Iterator<Course> i = controller.getCoursesList().iterator();
        while (i.hasNext())
            jCBcourseList.addItem(i.next());
        /*
         *
         */
        int numbOfDays = 90;
        jCBselectedDate.addItem("Select Date");
        while (--numbOfDays != 0) {
            if (todaysCalendar.get(Calendar.DAY_OF_WEEK) != todaysCalendar.SATURDAY &&
                todaysCalendar.get(Calendar.DAY_OF_WEEK) != todaysCalendar.SUNDAY)
                jCBselectedDate.addItem(controller.getDateformatter().format(todaysCalendar.getTime()));
            todaysCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        /*
         *
         */
        jCBRooms.addItem("Select Room");
        Iterator<Room> k = controller.getRoomList().iterator();
        while (k.hasNext())
            jCBRooms.addItem(k.next());
    }

    private void jButton1_actionPerformed(ActionEvent e) {
        try {
            Room selectedRoom = (Room) jCBRooms.getSelectedItem(); //store an object of type Room
            String selectedDate = (String)jCBselectedDate.getSelectedItem(); //store a date
            if (selectedDate.equals("Select Date"))
                throw new ControllerException("Please, select the date for the reservation you want to register.");
            Course selectedCourse = (Course) jCBcourseList.getSelectedItem(); //store an object of type course.
            Room newRoom =
                new Room(selectedRoom.getRoomTitle(), selectedDate, selectedCourse.getCourseTitle()); //instantiate an boject of type Room
            controller.emptyRoomAvail(newRoom); //check that there more than 2 available rooms.
            controller.checkRoomsRes(newRoom); //verify that the course or the date inputted by the user have not been already reserved to the date the user inputted
            controller.weeklyRoomReserv(newRoom); //verify that the room specified by the user has not been already reserved 3 times.
            Iterator<Room> i = controller.getRoomList().iterator();
            int sum = 0;
            while (i.hasNext()) {
                Room aRoom = i.next();
                if (newRoom.getRoomTitle().equals(aRoom.getRoomTitle()))
                    controller.getRoomList().get(sum).reserveRoom(newRoom.getDateTitle(), newRoom);
                sum++;
            }
            selectedDateReservation();
        } catch (NullPointerException empty) {
            JOptionPane.showMessageDialog(null,
                                          "Each reservation requires Course Title, Room Name, and Date to be reserved.",
                                          "Data Error", JOptionPane.WARNING_MESSAGE);
        } catch (ClassCastException empty) {
            JOptionPane.showMessageDialog(null,
                                          "Each reservation requires Course Title, Room Name, and Date to be reserved.",
                                          "Data Error", JOptionPane.WARNING_MESSAGE);

        } catch (ControllerException k) {
            JOptionPane.showMessageDialog(null, k.getMessage(), "Data Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void jComboBox1_actionPerformed(ActionEvent e) {
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    private void jCBRooms_actionPerformed(ActionEvent e) {

    }

    private void jCBselectedDate_actionPerformed(ActionEvent e) {
        selectedDateReservation();
    }

    private void selectedDateReservation() {
        jTAMMessageMenu.setText("");
        String outPut = "--------------------------------------------\n";
        String selectedDate = (String)jCBselectedDate.getSelectedItem();
        boolean found = false;
        Iterator<Room> i = controller.getRoomList().iterator();
        while (i.hasNext()) {
            Room currentRoom = i.next();
            if (currentRoom.reservedRoomList.containsKey(selectedDate)) {
                outPut +=
                        currentRoom.reservedRoomList.get(selectedDate).getCourseTitle() + " " + currentRoom.reservedRoomList.get(selectedDate).getRoomTitle() +
                        "\n";
                found = true;
            }
        }
        if (found) {
            jTAMMessageMenu.setText("Reservations on: " + selectedDate + "\n" +
                    outPut);
        } else if (!selectedDate.equals("Select Date"))
            jTAMMessageMenu.setText("No Rooms have been reserved on " + selectedDate + "\n" +
                    outPut);
    }


    private void jButton5_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void jButton4_actionPerformed(ActionEvent e) {
        try {
            Room theRoom = (Room) jCBRooms.getSelectedItem();
            Course theCourse = (Course) jCBcourseList.getSelectedItem();
            Room cancelReservation;
            Iterator<Room> roomList = controller.getRoomList().iterator();
            if (jCBselectedDate.getSelectedItem().equals("Select Date"))
                throw new ClassCastException();
            while (roomList.hasNext()) {
                Room aRoom = roomList.next();
                if (aRoom.getRoomTitle().equals(theRoom.getRoomTitle())) {
                    cancelReservation =
                            new Room(theRoom.getRoomTitle(), (String)jCBselectedDate.getSelectedItem(), theCourse.getCourseTitle()); //store an object of type Room
                    theRoom.cancelReservaion(cancelReservation);
                    selectedDateReservation();
                }
            }
        } catch (ClassCastException k) {
            JOptionPane.showMessageDialog(null, "Course, room, and date must be specified in order to cancel a lab.");
        }
    }

    private void jButton3_actionPerformed(ActionEvent e) {
        String outPut = "All Lab Reservations\n" +
                        "---------------------------------------\n";
        boolean found = false;
        Iterator<Room> i = controller.getRoomList().iterator();
        while (i.hasNext()) {
            String temp = i.next().displayDateReservedForThisRoom();
            if (!temp.equals("")) {
                found = true;
                outPut += temp + "\n";
            }
        }
        if (found)
            jTAMMessageMenu.setText(outPut);
        else
            jTAMMessageMenu.setText("No Reservation in database" + "\n" +
                    outPut);
    }


    private void jButton6_actionPerformed(ActionEvent e) {
        jTAMMessageMenu.setText("");
        String outPut = ": Today's Reservation\n---------------------------------------\n";
        String currentDate = controller.getDateformatter().format(Calendar.getInstance().getTime());
        boolean found = false;
        Iterator<Room> i = controller.getRoomList().iterator();
        while (i.hasNext()) {
            Room currentRoom = i.next();
            if (currentRoom.reservedRoomList.containsKey(currentDate)) {
                outPut +=
                        currentRoom.reservedRoomList.get(currentDate).getCourseTitle() + currentRoom.reservedRoomList.get(currentDate).getRoomTitle() +
                        "\n";
                found = true;
            }
        }
        if (found) {

            jTAMMessageMenu.setText(currentDate  +
                    outPut);
        } else
            jTAMMessageMenu.setText("No Rooms have been reserved for today" + "\n" +
                    outPut);

    }

    private void jButton7_actionPerformed(ActionEvent e) {
        displayTodaysEmptyRooms();
    }

    private void displayTodaysEmptyRooms() {
        jTAMMessageMenu.setText("");
        String currentDate = controller.getDateformatter().format(Calendar.getInstance().getTime());
        String output = "---------------------------------------\n";
        int size = 0;
        Iterator<Room> k = controller.getRoomList().iterator();
        ArrayList<String> unSortedList = new ArrayList<String>();
        while (k.hasNext()) {
            Room theRoom = k.next();
            if (!theRoom.reservedRoomList.containsKey(currentDate)) {
                output += theRoom.getRoomTitle() + "\n";
            }
            size++;
        }
        String output1 = ("---Today's Empty Rooms: " + size + "---\n" +
            output);
        jTAMMessageMenu.setText(output1);
    }
}
