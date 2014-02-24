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
 * This class contain various method that will be used by many classes. It
 * includes method to write and read data, writing and reading from database,
 * and verifying correct data.
 * 
 * @author Rizart Dokollari
 * @since November, 2012
 */

public class Controller implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<Integer, Instructor> instructors = new HashMap<Integer, Instructor>();
	private HashMap<Integer, Student> students = new HashMap<Integer, Student>();
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Calendar currentCalendar = Calendar.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"E MMM.dd.yyyy");

	/**
	 * Adds new data objects (courses, instructors, students) to the given hash
	 * map.
	 * 
	 * @param new_id
	 *           the key to check if given object to add already exist
	 * @param new_object
	 *           the new object to store in the hashmap, which includes
	 * @param givenHashmap
	 *           the hash map to be used to store the new objects
	 * @return true if object successfully added to given hashmap
	 * @throws ControllerException
	 *            when a user tries to input data that already exist the
	 *            CollegeExcpetion will make sure it doesn't allow for adding new
	 *            data to the hashmap
	 */
	public boolean addData(int new_id, Object new_object, HashMap givenHashmap)
			throws ControllerException {
		if (givenHashmap.containsKey(new_id)) {
			throw new ControllerException(new_id
					+ " is already reserved on database."
					+ "\nPlease choose a different ID.");
		} else if (givenHashmap.containsValue(new_object))
			throw new ControllerException(new_object
					+ " is already reserved on database."
					+ "\nPlease choose different first name or last name.");
		else {
			givenHashmap.put(new_id, new_object);
		}
		JOptionPane.showMessageDialog(null, new_object.toString()
				+ " successfully added.");
		return true;
	}

	/**
	 * Remove an ID with it's value. If the key does not exist, throws
	 * CollegeExcpetion
	 * 
	 * @param removingID
	 *           Integer ID that will be used as a key in a hashmap
	 * @param chosenHashMap
	 *           the hashmap list that will be used to delete from, a key with
	 *           it's value
	 * @return always return true, unless an exception occurs when the ID does
	 *         not exists
	 * @throws me.dokollari.college.manager.mvc.ControllerException
	 */
	public boolean removeData(int removingID,
			@SuppressWarnings("rawtypes") HashMap chosenHashMap)
			throws me.dokollari.college.manager.mvc.ControllerException {
		if (chosenHashMap.containsKey(removingID))
			throw new me.dokollari.college.manager.mvc.ControllerException(
					removingID + "ID does not exist on database.\n");
		return true;
	}

	/**
	 * Loop through a hashmap and store each of it's value to a String
	 * 
	 * @param hash_map
	 *           hashmap if type Instructor that will be used in order to get
	 *           data
	 * @return return string will the values of this hashmap
	 */
	public String listInstructorData(HashMap<Integer, Instructor> hash_map) {
		String list = "";
		for (Map.Entry<Integer, Instructor> e : hash_map.entrySet()) {
			list += e.getValue() + "\n";
		} // end for
		return list;
	}

	/**
	 * Create a string with all the values of the hashpmap
	 * 
	 * @param hash_map
	 *           ths list of type Student, will we be used to get student's data
	 * @return return a string that will hold all the students stored in this
	 *         hashmap list
	 */
	public String listStudentData(HashMap<Integer, Student> hash_map) {
		String list = "";
		for (Map.Entry<Integer, Student> e : hash_map.entrySet())
			list += e.getValue() + "\n";
		return list;
	}

	/**
	 * Loop and store value of an array list, to String.
	 * 
	 * @param listToView
	 *           use a list, of type Course and Room to get it's value for each
	 *           of its objects.
	 * @return a String will all the it's list default (toString) values
	 */
	public String listData(ArrayList<?> listToView) {
		String list = "";
		Iterator<?> i = listToView.iterator();
		while (i.hasNext()) {
			list += i.next() + "\n";
		}
		return list;

	}

	/**
	 * @param listToProcess
	 *           a list of type ArrayList that will be used to take data from
	 * @param newData
	 *           an object, that will be used to compare with the object of the
	 *           above list'
	 * @return return if true if add was added to the above Arraylist or throw
	 *         exception if data already exists
	 * @throws me.dokollari.college.manager.mvc.ControllerException
	 */
	@SuppressWarnings("unchecked")
	public boolean addData(
			@SuppressWarnings("rawtypes") ArrayList listToProcess, Object newData)
			throws me.dokollari.college.manager.mvc.ControllerException {
		boolean found = true;
		@SuppressWarnings("rawtypes")
		Iterator i = listToProcess.iterator();
		while (i.hasNext())
			if (newData.equals(i.next()))
				throw new me.dokollari.college.manager.mvc.ControllerException(
						"Data entered, with details: " + newData.toString()
								+ "\nalready exists on database.");
		listToProcess.add(newData);
		return found;
	}

	/**
	 * Loop through array list of rooms. Store each room to a current Room. use
	 * this current Room to check it's hashmap list, whether it has a key that
	 * represent a date of type String. If it finds this key increase an integer
	 * number that we had initialized to 0. When this loop ends, check if the
	 * number of rooms minus the number of reservation is equal or less than 2
	 * then throw ControllerException since we want at least 2 rooms to be empty
	 * 
	 * @param theRoom
	 *           an object of type Room that will be used in order check various
	 *           condition
	 * @throws me.dokollari.college.manager.mvc.ControllerException
	 *            if the above condition are found, will throw
	 *            ControllerException, els will do nothing.
	 */
	public void emptyRoomAvail(Room theRoom)
			throws me.dokollari.college.manager.mvc.ControllerException {
		int numReser = 0;
		Iterator<Room> i = rooms.iterator();
		while (i.hasNext()) {
			Room currentRoom = i.next();
			if (currentRoom.getReservedRoomList().containsKey(
					theRoom.getDateTitle())) {
				numReser++;
			}
		}
		if (rooms.size() - numReser <= 2)
			throw new me.dokollari.college.manager.mvc.ControllerException(
					"No more rooms can be reserved on " + theRoom.getDateTitle()
							+ ".At least 2 rooms must be empty for special cases.");
	}

	/**
	 * This method will make sure that no room is reseverd more than once per
	 * day. Same will do with Courses.
	 * 
	 * @param selectedRoom
	 *           represents the reservation that the user wants to reserve
	 * @throws me.dokollari.college.manager.mvc.ControllerException
	 *            if the user tries to reserve a course, or a room more than once
	 *            on one day then it will throws ControllerException
	 */
	public void checkRoomsRes(Room selectedRoom)
			throws me.dokollari.college.manager.mvc.ControllerException {
		Iterator<Room> i = rooms.iterator();
		while (i.hasNext()) {
			Room loopRoom = i.next();
			if (loopRoom.getReservedRoomList().containsKey(
					selectedRoom.getDateTitle())) {
				Room checkRoom = loopRoom.getReservedRoomList().get(
						selectedRoom.getDateTitle());
				if (checkRoom.getCourseTitle()
						.equals(selectedRoom.getCourseTitle()))
					throw new me.dokollari.college.manager.mvc.ControllerException(
							"Course: \"" + checkRoom.getCourseTitle()
									+ "\" can be reserved at most once per day.");
				if (checkRoom.getRoomTitle().equals(selectedRoom.getRoomTitle()))
					throw new me.dokollari.college.manager.mvc.ControllerException(
							"Room: \"" + checkRoom.getRoomTitle()
									+ "\" can be reserved at most once per day.");
			}
		}
	}

	/**
	 * this method will make sure, that at least 2 empty rooms will always be
	 * empty. It loop for each room. In each loop loop will make a number MAX =
	 * 3. Then it will store this Room to a temporary currentRoom. Then it will
	 * loop for each room of course, through all the reservations that each room
	 * has. then using appropriate code will compare the week of years between
	 * each reservation and the date the user had chosen. It will allow 3 times
	 * for the reservations to be done since we have 5 work days.
	 * 
	 * @param selectedRoom
	 *           An object of type Room that will hold date, roomName, and
	 *           courseName
	 * @throws me.dokollari.college.manager.mvc.ControllerException
	 *            Will throw ControllerException in case three reservation have
	 *            been already done.
	 */
	public void weeklyRoomReserv(Room selectedRoom)
			throws me.dokollari.college.manager.mvc.ControllerException {
		Calendar selectedCal = convertStringToCalendar(selectedRoom
				.getDateTitle());
		Iterator<Room> i = rooms.iterator();
		Calendar currentCal;
		while (i.hasNext()) {
			int maxReserPerWeek = 3;
			Room currentRoom = i.next();
			for (Map.Entry<String, Room> e : currentRoom.getReservedRoomList()
					.entrySet()) { // loop through all the entries of hash map
				currentCal = convertStringToCalendar(e.getValue().getDateTitle());
				if (currentCal.get(currentCal.getWeekYear()) == selectedCal
						.get(selectedCal.getWeekYear())) {
					maxReserPerWeek--;
				} // end if
			}
			if (maxReserPerWeek == 0) {
				throw new me.dokollari.college.manager.mvc.ControllerException(
						"Each room can be reserved at most 3 times per week.\nReservation denied.");
			} // end if
		}

	}

	/**
	 * This method will convert a String back to it's equivalent value. We do
	 * this in order to use some commands that allows us to take week of year.
	 * 
	 * @param date
	 *           A string representing an object.
	 * @return Will return the equivalent of a string date to calendar type
	 *         object.
	 */
	private Calendar convertStringToCalendar(String date) {
		Calendar selectedC = Calendar.getInstance();
		try {
			String input = date;
			Date theDate = simpleDateFormat.parse(input);
			selectedC.setTime(theDate);
		} catch (ParseException k) {
			System.out.println("Cannot parse.");
		}
		return selectedC;
	}

	/**
	 * 
	 * This method will retrieve data from the corresponding database.
	 * 
	 * @throws SQLException
	 *            Will throw exception(appropriate message) in case connection
	 *            can not done.
	 */
	public void retrieveData(JProgressBar jPB_db) throws ControllerException {
		try {
			DB db = new DB(jPB_db);

			ResultSet studentsSet = db.getStudents();

			while (studentsSet.next()) { // while db has more students.
				int studentID = studentsSet.getInt(DB_queries.STUDENT_ID);
				String studentLastName = studentsSet
						.getString(DB_queries.STUDENT_LAST_NAME);
				String studentFirstName = studentsSet
						.getString(DB_queries.STUDENT_FIRST_NAME);
				int advisorID = studentsSet.getInt(DB_queries.STUDENT_ADVISOR_ID);
				Student student = new Student(studentID, studentLastName,
						studentFirstName);
				student.setAdvisor(advisorID);
				students.put(studentID, student);
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
					if (currentCourse.getCourseTitle().equals(
							students_courses_Set.getString("course_title"))) {
						currentCourse.registerStudent(students
								.get(students_courses_Set.getInt("id")));
					} // end if
				}
				number_of_courses++;
			}

			ResultSet instructorSet = db.getInstructors();

			while (instructorSet.next()) {
				int instructorID = instructorSet.getInt("id");
				String lastName = instructorSet.getString("last_name");
				String firstName = instructorSet.getString("first_name");
				instructors.put(instructorID, new Instructor(lastName, firstName,
						instructorID));
			}

			ResultSet roomsSet = db.getRooms();
			ResultSet reservedRoomSet = db.getReservedRooms();

			int roomEntry = 0;
			while (roomsSet.next()) {
				String emptyRoomTitle = roomsSet.getString("title");

				Room room = new Room(emptyRoomTitle);
				rooms.add(room);

				Room currentRoom = getRoomList().get(roomEntry);
				while (reservedRoomSet.next()) {
					String dateReserved = reservedRoomSet.getString("date");
					String reservedRoomTitle = reservedRoomSet.getString("title");

					if (emptyRoomTitle.equals(currentRoom.getRoomTitle())) {
						room.getReservedRoomList().put(
								dateReserved,
								new Room(emptyRoomTitle, dateReserved,
										reservedRoomTitle));
					} // end if
				} // end while
				roomEntry++;
			}

			studentsSet.close();
			coursesSet.close();
			students_courses_Set.close();
			instructorSet.close();
			roomsSet.close();
			reservedRoomSet.close();

			jPB_db.setString(Integer.toString(jPB_db.getValue())
					+ "% All data succesfully retrieved");

		} catch (SQLException k) {
			jPB_db.setString(Integer.toString(jPB_db.getValue())
					+ "% Critical Error. Hover for details.");
			jPB_db.setToolTipText(k.getMessage());

		}
	}

	/**
	 * This method will write data to the database.
	 * 
	 * @throws SQLException
	 *            It will remind the user that the data is losed since no
	 *            connection is available.
	 */
	public void writeData(JProgressBar jPB_db) throws SQLException {

		// try {
		// DB db = new DB(jPB_db);
		// // Connection myConnection = db.getDBConnection();
		// Statement myStatement = myConnection.createStatement();
		// Statement myStatement1 = myConnection.createStatement();
		//
		// myStatement.executeUpdate("delete from students");
		// for (Map.Entry<Integer, Student> e : studentsList.entrySet()) {
		// myStatement.executeUpdate("insert into students (student_id, last_name, first_name, advisor_id)values("
		// +
		// e.getValue().getId() + ", '" + e.getValue().getLastName() + "','" +
		// e.getValue().getFirstName() + "'," + e.getValue().getAdvisor() + ")");
		// }
		//
		// myStatement.executeUpdate("delete from instructors");
		// for (Map.Entry<Integer, Instructor> e : instructorsList.entrySet()) {
		// myStatement.executeUpdate("insert into instructors (last_name, first_name, instructor_id)values('"
		// +
		// e.getValue().getLastName() + "','" + e.getValue().getFirstName() + "',"
		// +
		// e.getValue().getInstructorID() + ")");
		// }
		//
		// myStatement.executeUpdate("delete from courses");
		// myStatement.executeUpdate("delete from students_course");
		// Iterator<Course> i = courses.iterator();
		// while (i.hasNext()) {
		// Course a_course = i.next();
		// myStatement.executeUpdate("insert into courses (course_title, students_limit, instructor_id ) values('"
		// +
		// a_course.getCourseTitle() + "', " + a_course.getStudentLimit() + ", " +
		// a_course.getInstructorID() + ")");
		// for (Map.Entry<Integer, Student> e :
		// a_course.getStudents().entrySet())
		// myStatement.executeUpdate("insert into students_course(course_title, student_id)values('"
		// +
		// a_course.getCourseTitle() + "', " + e.getValue().getId());
		// }
		//
		// myStatement.executeUpdate("delete from rooms");
		// myStatement.executeUpdate("delete from reserved_rooms");
		// Iterator<Room> k = roomList.iterator();
		// while (k.hasNext()) {
		// Room a_room = k.next();
		// myStatement.executeUpdate("insert into rooms (room_title)values('" +
		// a_room.getRoomTitle() + "')");
		// for (Map.Entry<String, Room> e :
		// a_room.getReservedRoomList().entrySet()) {
		// myStatement1.executeUpdate("insert into reserved_rooms (room_title, date_title, course_title)values('"
		// +
		// e.getValue().getRoomTitle() + "','" + e.getValue().getDateTitle() +
		// "','" + e.getValue().getCourseTitle() + "')");
		// }
		//
		// }
		// myStatement.close();
		// myStatement1.close();
		// myConnection.close();
		// } catch (SQLException k) {
		// JOptionPane.showMessageDialog(null,
		// "Thank you for using this program. Your data has been deleted.");
		// }
	}

	public void verifyFacultyData(String lastName, String firstName, String ID,
			String choice)
			throws me.dokollari.college.manager.mvc.ControllerException {
		if (!Pattern.matches("[a-zA-Z.]+", lastName))
			throw new me.dokollari.college.manager.mvc.ControllerException(
					"Last Name can contain only letters with no spaces.");
		if (!Pattern.matches("[a-zA-Z.]+", firstName))
			throw new me.dokollari.college.manager.mvc.ControllerException(
					"First Name can contain only letters with no spaces.");
		if (choice.equals("Instructor"))
			if (!Pattern.matches("[0-9]+", ID) || ID.length() != 4)
				throw new me.dokollari.college.manager.mvc.ControllerException(
						choice + " ID must an integer number of length 4.");
		if (choice.equals("Student"))
			if (!Pattern.matches("[0-9]+", ID) || ID.length() != 5)
				throw new me.dokollari.college.manager.mvc.ControllerException(
						choice + " ID must an integer number of length 5.");

	}

	public void verifyRoomTitle(String roomTitle)
			throws me.dokollari.college.manager.mvc.ControllerException {
		if (!Pattern.matches("[a-zA-Z[0-9]]+", roomTitle))
			throw new me.dokollari.college.manager.mvc.ControllerException(
					"Room Title can contain only letters and number, with no spaces.");
	}

	public void verifyNumber(String number, String errorMessage)
			throws me.dokollari.college.manager.mvc.ControllerException {
		if (!Pattern.matches("[0-9]+", number))
			throw new me.dokollari.college.manager.mvc.ControllerException("\""
					+ errorMessage + "\" must contain only digits.");
		if (errorMessage.equals("ID"))
			if (number.length() != 5)
				throw new me.dokollari.college.manager.mvc.ControllerException("\""
						+ errorMessage + "\" must contain 5 digits.");
		if (errorMessage.equals("Student Limit"))
			if (Integer.parseInt(number) < 5 || Integer.parseInt(number) > 50)
				throw new me.dokollari.college.manager.mvc.ControllerException("\""
						+ errorMessage
						+ "\" must be an integer number between 3 and 50 students");
		if (errorMessage.equals("Instructor ID"))
			if (number.length() != 4)
				throw new me.dokollari.college.manager.mvc.ControllerException("\""
						+ errorMessage + "\" must contain 4 digits.");
	}

	public void verifyTitle(String title)
			throws me.dokollari.college.manager.mvc.ControllerException {
		if (!Pattern.matches("[a-zA-Z[0-9][' ']]+", title))
			throw new me.dokollari.college.manager.mvc.ControllerException(
					"Course title may consists only of letters, numbers, and spaces.");
	}

	public static String duplicateData(Object theObject) {
		return "Error\n\"" + theObject.toString()
				+ "\" already exists on database.";
	}

	public HashMap<Integer, Student> getStudentsList() {
		return students;
	}

	public ArrayList<Room> getRoomList() {
		return rooms;
	} // list with physical rooms

	public HashMap<Integer, Instructor> getInstructorsList() {
		return instructors;
	}

	public ArrayList<Course> getCoursesList() {
		return courses;
	}

	public Calendar getCurrentDate() {
		return currentCalendar;
	}

	public SimpleDateFormat getDateformatter() {
		return simpleDateFormat;
	}

}
