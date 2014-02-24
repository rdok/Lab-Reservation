package me.dokollari.college.manager.mvc;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import me.dokollari.college.manager.models.Course;
import me.dokollari.college.manager.models.Instructor;
import me.dokollari.college.manager.models.Room;
import me.dokollari.college.manager.models.Student;

/**
 *
 * This class contain various method that will be used by many classes. It includes
 * method to write and read data, writing and reading from database, and verifying correct data.
 *
 * @author Rizart Dokollari
 * @since November, 2012
 */

public class Controller implements Serializable {

    private HashMap<Integer, me.dokollari.college.manager.models.Instructor> instructorsList =
        new HashMap<Integer, me.dokollari.college.manager.models.Instructor>();

    private HashMap<Integer, me.dokollari.college.manager.models.Student> studentsList =
        new HashMap<Integer, me.dokollari.college.manager.models.Student>();

    private ArrayList<me.dokollari.college.manager.models.Course> courses =
        new ArrayList<me.dokollari.college.manager.models.Course>();

    private ArrayList<me.dokollari.college.manager.models.Room> roomList =
        new ArrayList<me.dokollari.college.manager.models.Room>();

    private Calendar currentDate = Calendar.getInstance();
    private SimpleDateFormat dateformatter = new SimpleDateFormat("E MMM.dd.yyyy");


    /**
     * This method will add only new Data  to a hashmap
     *
     * @param new_id the key to be used in the hashmap list
     * @param new_object the new object to be store in the hashmap, which includes Instructor, and Student class
     * @param chosenHashMap the hash map to be used to store the new objects
     * @return return a boolean variable if the
     * @throws ControllerException when a user tries to input data that already exist the CollegeExcpetion will
     * make sure it doesn't allow for adding new data to the hashmap
     */
    public boolean addData(int new_id, Object new_object, HashMap chosenHashMap) throws ControllerException {
        if (chosenHashMap.containsKey(new_id)) {
            throw new ControllerException(new_id + " is already reserved on database." +
                                          "\nPlease choose a different ID.");
        } else if (chosenHashMap.containsValue(new_object))
            throw new ControllerException(new_object + " is already reserved on database." +
                                          "\nPlease choose different first name or last name.");
        else {
            chosenHashMap.put(new_id, new_object);
        }
        JOptionPane.showMessageDialog(null, new_object.toString() + " successfully added.");
        return true;
    }

    /**
     * Remove an ID with it's value. If the key does not exist, throws CollegeExcpetion
     *
     * @param removingID Integer ID taht will be used as a key in a hashmap
     * @param chosenHashMap the hashmap list that will be used to delete from, a key with it's value
     * @return always return true, unless an exception occurs when the ID does not exists
     * @throws me.dokollari.college.manager.mvc.ControllerException
     */
    public boolean removeData(int removingID,
                              HashMap chosenHashMap) throws me.dokollari.college.manager.mvc.ControllerException {
        if (chosenHashMap.containsKey(removingID))
            throw new me.dokollari.college.manager.mvc.ControllerException(removingID +
                                                                           "ID does not exist on database.\n");
        return true;
    }

    /**
     * Loop through a hashmap and store each of it's value to a String
     *
     * @param hash_map hashmap if type Instrucotr that will be used in order to get data
     * @return return string will the values of this hashmap
     */
    public String listInstructorData(HashMap<Integer, me.dokollari.college.manager.models.Instructor> hash_map) {
        String list = "";
        for (Map.Entry<Integer, me.dokollari.college.manager.models.Instructor> e : hash_map.entrySet()) {
            list += e.getValue() + "\n";
        } // end for
        return list;
    }

    /**
     * Create a string with all the values of the hashpmap
     *
     * @param hash_map ths list of type Student, will we be used to get student's data
     * @return return a string that will hold all the students stored in this hashmap list
     */
    public String listStudentData(HashMap<Integer, me.dokollari.college.manager.models.Student> hash_map) {
        String list = "";
        for (Map.Entry<Integer, me.dokollari.college.manager.models.Student> e : hash_map.entrySet())
            list += e.getValue() + "\n";
        return list;
    }


    /**
     * Loop and store value of an arraylist, to String.
     * @param listToView use a list, of type Course and Room to get it's value for each of its objects.
     * @return a String will all the it's list default (toString) values
     */
    public String listData(ArrayList listToView) {
        String list = "";
        Iterator i = listToView.iterator();
        while (i.hasNext()) {
            list += i.next() + "\n";
        }
        return list;

    }

    /**
     * @param listToProcess a list of type ArrayList that will be used to take data from
     * @param newData an object, that will be used to compare with the object of the above list'
     * @return return if true if add was added to the above Arraylist or throw exception if data
     * already exists
     * @throws me.dokollari.college.manager.mvc.ControllerException
     */
    public boolean addData(ArrayList listToProcess,
                           Object newData) throws me.dokollari.college.manager.mvc.ControllerException {
        boolean found = true;
        Iterator i = listToProcess.iterator();
        while (i.hasNext())
            if (newData.equals(i.next()))
                throw new me.dokollari.college.manager.mvc.ControllerException("Data entered, with details: " +
                                                                               newData.toString() +
                                                                               "\nalready exists on database.");
        listToProcess.add(newData);
        return found;
    }

    /**
     * Loop through arraylist of rooms. Store each room to a current Room.
     * use this current Room to check it's hashmap list, whether it has a key
     * that represetnt a date of type String. If it finds this key increase an integer
     * number that we had initialized to 0. When this loop ends, check
     * if the number of rooms minus the number of reservation is equal or less than 2
     * then throw ControllerException since we want at least 2 rooms to be empty
     *
     * @param theRoom an object of type Room that will be used in order check various condition
     * @throws me.dokollari.college.manager.mvc.ControllerException if the above condition are found, will throw ControllerException,
     * els will do nothing.
     */
    public void emptyRoomAvail(me.dokollari.college.manager.models.Room theRoom) throws me.dokollari.college.manager.mvc.ControllerException {
        int numReser = 0;
        Iterator<me.dokollari.college.manager.models.Room> i = roomList.iterator();
        while (i.hasNext()) {
            me.dokollari.college.manager.models.Room currentRoom = i.next();
            if (currentRoom.getReservedRoomList().containsKey(theRoom.getDateTitle())) {
                numReser++;
            }
        }
        if (roomList.size() - numReser <= 2)
            throw new me.dokollari.college.manager.mvc.ControllerException("No more rooms can be reserved on " +
                                                                           theRoom.getDateTitle() +
                                                                           ".At least 2 rooms must be empty for special cases.");
    }


    /**
     * This method will make sure that no room is reseverd more than once per day. Same
     * will do with Courses.
     *
     * @param selectedRoom represents the reservation that the user wants to reserve
     * @throws me.dokollari.college.manager.mvc.ControllerException if the user tries to reserve a course, or a room
     * more than once on one day then it will throws ControllerException
     */
    public void checkRoomsRes(me.dokollari.college.manager.models.Room selectedRoom) throws me.dokollari.college.manager.mvc.ControllerException {
        Iterator<me.dokollari.college.manager.models.Room> i = roomList.iterator();
        while (i.hasNext()) {
            me.dokollari.college.manager.models.Room loopRoom = i.next();
            if (loopRoom.getReservedRoomList().containsKey(selectedRoom.getDateTitle())) {
                me.dokollari.college.manager.models.Room checkRoom =
                    loopRoom.getReservedRoomList().get(selectedRoom.getDateTitle());
                if (checkRoom.getCourseTitle().equals(selectedRoom.getCourseTitle()))
                    throw new me.dokollari.college.manager.mvc.ControllerException("Course: \"" +
                                                                                   checkRoom.getCourseTitle() +
                                                                                   "\" can be reserved at most once per day.");
                if (checkRoom.getRoomTitle().equals(selectedRoom.getRoomTitle()))
                    throw new me.dokollari.college.manager.mvc.ControllerException("Room: \"" +
                                                                                   checkRoom.getRoomTitle() +
                                                                                   "\" can be reserved at most once per day.");
            }
        }
    }


    /**
     * this method will make sure, that at least 2 empty rooms will alway be empty.
     * It loop for each room. In each loop loop will make a number  MAX = 3. Then
     * it will store this Room to a temporary currentRoom. Then it will loop
     * for each room ofcourse, through all the reservations that each room has.
     * then using appropirate code will compare the week of years betweeen each reservation
     * and the date the user had chosen. It will allow 3 times for the reservations to be done
     * since we have 5 workdays.
     *
     * @param selectedRoom An object of type Room that will hold date, roomName, and courseName
     * @throws me.dokollari.college.manager.mvc.ControllerException Will throw ControllerException in case three reservation have been
     * already done.
     */
    public void weeklyRoomReserv(me.dokollari.college.manager.models.Room selectedRoom) throws me.dokollari.college.manager.mvc.ControllerException {
        Calendar selectedCal = convertStringToCalendar(selectedRoom.getDateTitle());
        Iterator<me.dokollari.college.manager.models.Room> i = roomList.iterator();
        Calendar currentCal;
        while (i.hasNext()) {
            int maxReserPerWeek = 3;
            me.dokollari.college.manager.models.Room currentRoom = i.next();
            for (Map.Entry<String, me.dokollari.college.manager.models.Room> e : currentRoom.getReservedRoomList().entrySet()) { //loop through all the entries of hash map
                currentCal = convertStringToCalendar(e.getValue().getDateTitle());
                if (currentCal.get(currentCal.WEEK_OF_YEAR) == selectedCal.get(selectedCal.WEEK_OF_YEAR))
                    maxReserPerWeek--;
            }
            if (maxReserPerWeek == 0) {
                throw new me.dokollari.college.manager.mvc.ControllerException("Each room can be reserved at most 3 times per week.\nReservation denied.");
            } // end if
        }

    }

    /**
     * This method will convert a String back to it's equivalen value.
     * We do this in order to use some commands that allows us to take week of year.
     *
     * @param date A string representing an object.
     * @return Will return the equvalent of a string date to calendar type object.
     */
    private Calendar convertStringToCalendar(String date) {
        Calendar selectedC = Calendar.getInstance();
        try {
            String input = date;
            Date theDate = dateformatter.parse(input);
            selectedC.setTime(theDate);
        } catch (ParseException k) {
            System.out.println("Cannot parse.");
        }
        return selectedC;
    }


    /**
     *
     *This method will retrieve data from the corresponding database.
     *
     * @throws SQLException Will throw excpetion(appropirate message) in case
     * connectino can not done.
     */
    public void retrieveData(JProgressBar jPB_db) throws ControllerException {
        try {
            DB db = new DB(jPB_db);

            ResultSet students = db.getStudents();

            while (students.next()) { // while db has more students.
                int studentID = students.getInt(DB_queries.STUDENT_ID);
                String studentLastName = students.getString(DB_queries.STUDENT_LAST_NAME);
                String studentFirstName = students.getString(DB_queries.STUDENT_FIRST_NAME);
                int advisorID = students.getInt(DB_queries.STUDENT_ADVISOR_ID);
                Student student = new Student(studentID, studentLastName, studentFirstName);
                student.setAdvisor(advisorID);
                studentsList.put(studentID, student);
            } // end while

            ResultSet coursesSet = db.getCourses();
            ResultSet students_courses_Set = db.getCourseStudents();

            int number_of_courses = 0;

            while (coursesSet.next()) {
                String courseTitle = coursesSet.getString("title");
                int studentsLimit = coursesSet.getInt("students_limit");
                int instructorID = coursesSet.getInt("instructor_id");
                courses.add(new Course(courseTitle, studentsLimit, instructorID));

                // check current course (JOIN)
                Course currentCourse = courses.get(number_of_courses);
                while (students_courses_Set.next()) {
                    if (currentCourse.getCourseTitle().equals(students_courses_Set.getString("course_title"))) {
                        currentCourse.registerStudent(studentsList.get(students_courses_Set.getInt("id")));
                    } // end if
                }
                number_of_courses++;
            }

            ResultSet instructorSet = db.getInstructors();
            
            while (instructorSet.next()) {
                int instructorID = instructorSet.getInt("id");
                String lastName = instructorSet.getString("last_name");
                String firstName = instructorSet.getString("first_name");
                instructorsList.put(instructorID, new Instructor(lastName, firstName, instructorID));
            }

            ResultSet roomsSet = db.getRooms();
            ResultSet reservedRoomSet = db.getReservedRooms();

            int roomEntry = 0;
            while (roomsSet.next()) {
                String emptyRoomTitle = roomsSet.getString("title");

                Room room = new Room(emptyRoomTitle);
                roomList.add(room);

                Room currentRoom = getRoomList().get(roomEntry);
                while (reservedRoomSet.next()) {
                    String dateReserved = reservedRoomSet.getString("date");
                    String reservedRoomTitle = reservedRoomSet.getString("title");

                    if (emptyRoomTitle.equals(currentRoom.getRoomTitle())) {
                        room.getReservedRoomList().put(dateReserved,
                                                       new Room(emptyRoomTitle, dateReserved, reservedRoomTitle));
                    } // end if
                } // end while
                roomEntry++;
            }

            students.close();
            coursesSet.close();
            students_courses_Set.close();
            instructorSet.close();
            roomsSet.close();
            reservedRoomSet.close();

            jPB_db.setString(Integer.toString(jPB_db.getValue()) + "% All data succesfully retrieved");

        } catch (SQLException k) {
            jPB_db.setString(Integer.toString(jPB_db.getValue()) + "% Critical Error. Hover for details.");
            jPB_db.setToolTipText(k.getMessage());

        }
    }


    /**
     *This method will write data to the database.
     *
     * @throws SQLException It will remind the user that the data is losed since no connection is available.
     */
    public void writeData(JProgressBar jPB_db) throws SQLException {

        //        try {
        //            DB db = new DB(jPB_db);
        //           // Connection myConnection = db.getDBConnection();
        //            Statement myStatement = myConnection.createStatement();
        //            Statement myStatement1 = myConnection.createStatement();
        //
        //            myStatement.executeUpdate("delete from students");
        //            for (Map.Entry<Integer, me.dokollari.college.manager.models.Student> e : studentsList.entrySet()) {
        //                myStatement.executeUpdate("insert into students (student_id, last_name, first_name, advisor_id)values(" +
        //                                          e.getValue().getId() + ", '" + e.getValue().getLastName() + "','" +
        //                                          e.getValue().getFirstName() + "'," + e.getValue().getAdvisor() + ")");
        //            }
        //
        //            myStatement.executeUpdate("delete from instructors");
        //            for (Map.Entry<Integer, me.dokollari.college.manager.models.Instructor> e : instructorsList.entrySet()) {
        //                myStatement.executeUpdate("insert into instructors (last_name, first_name, instructor_id)values('" +
        //                                          e.getValue().getLastName() + "','" + e.getValue().getFirstName() + "'," +
        //                                          e.getValue().getInstructorID() + ")");
        //            }
        //
        //            myStatement.executeUpdate("delete from courses");
        //            myStatement.executeUpdate("delete from students_course");
        //            Iterator<me.dokollari.college.manager.models.Course> i = courses.iterator();
        //            while (i.hasNext()) {
        //                me.dokollari.college.manager.models.Course a_course = i.next();
        //                myStatement.executeUpdate("insert into courses (course_title, students_limit, instructor_id ) values('" +
        //                                          a_course.getCourseTitle() + "', " + a_course.getStudentLimit() + ", " +
        //                                          a_course.getInstructorID() + ")");
        //                for (Map.Entry<Integer, me.dokollari.college.manager.models.Student> e :
        //                     a_course.getStudents().entrySet())
        //                    myStatement.executeUpdate("insert into students_course(course_title, student_id)values('" +
        //                                              a_course.getCourseTitle() + "', " + e.getValue().getId());
        //            }
        //
        //            myStatement.executeUpdate("delete from rooms");
        //            myStatement.executeUpdate("delete from reserved_rooms");
        //            Iterator<me.dokollari.college.manager.models.Room> k = roomList.iterator();
        //            while (k.hasNext()) {
        //                me.dokollari.college.manager.models.Room a_room = k.next();
        //                myStatement.executeUpdate("insert into rooms (room_title)values('" + a_room.getRoomTitle() + "')");
        //                for (Map.Entry<String, me.dokollari.college.manager.models.Room> e :
        //                     a_room.getReservedRoomList().entrySet()) {
        //                    myStatement1.executeUpdate("insert into reserved_rooms (room_title, date_title, course_title)values('" +
        //                                               e.getValue().getRoomTitle() + "','" + e.getValue().getDateTitle() +
        //                                               "','" + e.getValue().getCourseTitle() + "')");
        //                }
        //
        //            }
        //            myStatement.close();
        //            myStatement1.close();
        //            myConnection.close();
        //        } catch (SQLException k) {
        //            JOptionPane.showMessageDialog(null, "Thank you for using this program. Your data has been deleted.");
        //        }
    }


    public void verifyFacultyData(String lastName, String firstName, String ID,
                                  String choice) throws me.dokollari.college.manager.mvc.ControllerException {
        if (!Pattern.matches("[a-zA-Z.]+", lastName))
            throw new me.dokollari.college.manager.mvc.ControllerException("Last Name can contain only letters with no spaces.");
        if (!Pattern.matches("[a-zA-Z.]+", firstName))
            throw new me.dokollari.college.manager.mvc.ControllerException("First Name can contain only letters with no spaces.");
        if (choice.equals("Instructor"))
            if (!Pattern.matches("[0-9]+", ID) || ID.length() != 4)
                throw new me.dokollari.college.manager.mvc.ControllerException(choice +
                                                                               " ID must an integer number of length 4.");
        if (choice.equals("Student"))
            if (!Pattern.matches("[0-9]+", ID) || ID.length() != 5)
                throw new me.dokollari.college.manager.mvc.ControllerException(choice +
                                                                               " ID must an integer number of length 5.");

    }

    public void verifyRoomTitle(String roomTitle) throws me.dokollari.college.manager.mvc.ControllerException {
        if (!Pattern.matches("[a-zA-Z[0-9]]+", roomTitle))
            throw new me.dokollari.college.manager.mvc.ControllerException("Room Title can contain only letters and number, with no spaces.");
    }

    public void verifyNumber(String number,
                             String errorMessage) throws me.dokollari.college.manager.mvc.ControllerException {
        if (!Pattern.matches("[0-9]+", number))
            throw new me.dokollari.college.manager.mvc.ControllerException("\"" + errorMessage +
                                                                           "\" must contain only digits.");
        if (errorMessage.equals("ID"))
            if (number.length() != 5)
                throw new me.dokollari.college.manager.mvc.ControllerException("\"" + errorMessage +
                                                                               "\" must contain 5 digits.");
        if (errorMessage.equals("Student Limit"))
            if (Integer.parseInt(number) < 5 || Integer.parseInt(number) > 50)
                throw new me.dokollari.college.manager.mvc.ControllerException("\"" + errorMessage +
                                                                               "\" must be an integer number between 3 and 50 students");
        if (errorMessage.equals("Instructor ID"))
            if (number.length() != 4)
                throw new me.dokollari.college.manager.mvc.ControllerException("\"" + errorMessage +
                                                                               "\" must contain 4 digits.");
    }

    public void verifyTitle(String title) throws me.dokollari.college.manager.mvc.ControllerException {
        if (!Pattern.matches("[a-zA-Z[0-9][' ']]+", title))
            throw new me.dokollari.college.manager.mvc.ControllerException("Course title may consists only of letters, numbers, and spaces.");
    }

    public static String duplicateData(Object theObject) {
        return "Error\n\"" + theObject.toString() + "\" already exists on database.";
    }


    public HashMap<Integer, Student> getStudentsList() {
        return studentsList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    } //list with physical rooms

    public HashMap<Integer, Instructor> getInstructorsList() {
        return instructorsList;
    }

    public ArrayList<Course> getCoursesList() {
        return courses;
    }

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public SimpleDateFormat getDateformatter() {
        return dateformatter;
    }

}
