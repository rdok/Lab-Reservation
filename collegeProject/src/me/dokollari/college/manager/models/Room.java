package me.dokollari.college.manager.models;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.mvc.ControllerException;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class Room implements Serializable {
    //what this day will store?

    private String roomTitle;
    private String dateTitle;
    private String courseTitle;

    private HashMap<String, me.dokollari.college.manager.models.Room> reservedRoomList;
    SimpleDateFormat dateformatter = new SimpleDateFormat("E MMM.dd.yyyy");

    //create a room

    public Room(String room_title) {
        this.roomTitle = room_title;
        dateTitle = null;
        reservedRoomList = new HashMap<String, me.dokollari.college.manager.models.Room>();
    }

    public Room(String room_title, String date_title, String course_title) {
        this.roomTitle = room_title;
        this.dateTitle = date_title;
        this.courseTitle = course_title;
    }

    public void reserveRoom(String newDate, me.dokollari.college.manager.models.Room reservRoom) throws ControllerException {
        if (reservedRoomList.containsKey(newDate))
            throw new ControllerException(Controller.duplicateData(reservRoom));
        reservedRoomList.put(newDate, reservRoom);
        JOptionPane.showMessageDialog(null, reservRoom.getRoomTitle() + " successfully reserved");
    }

    public void cancelReservaion(me.dokollari.college.manager.models.Room cancRes) {
        if (reservedRoomList.containsKey(cancRes.getDateTitle())) {
            reservedRoomList.remove(cancRes.getDateTitle());
            JOptionPane.showMessageDialog(null, "Reservation Canceled", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "Reservation Not Found", "Failure", JOptionPane.WARNING_MESSAGE);

    }

    public String displayDateReservedForThisRoom() {
        String list = "";
        for (Map.Entry<String, me.dokollari.college.manager.models.Room> e : reservedRoomList.entrySet())
            list += e.getValue() + "\n";
        return list;
    }

    public void setRoomTitle(String roomCreated) {
        this.roomTitle = roomCreated;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getDateTitle() {
        if (dateTitle != null)
            return dateTitle;
        return null;
    }


    public void setDateTitle(String date_title) {
        this.dateTitle = date_title;
    }

    public String toString() {
        if (this.dateTitle != null)
            return roomTitle + " " + courseTitle + " " + dateTitle;
        return roomTitle;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof me.dokollari.college.manager.models.Room)) {
            return false;
        }
        final me.dokollari.college.manager.models.Room other = (me.dokollari.college.manager.models.Room)object;

        if (other.getDateTitle() == null) { //for creating rooms. -->arrayList will use it(college_.roomList)
            if (!(roomTitle == null ? other == null : roomTitle.equals(other.roomTitle)))
                return false;
        }
        return true;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public HashMap<String, Room> getReservedRoomList() {
        return reservedRoomList;
    }
}
