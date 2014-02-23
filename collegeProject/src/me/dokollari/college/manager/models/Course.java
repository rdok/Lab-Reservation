package me.dokollari.college.manager.models;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import me.dokollari.college.manager.mvc.ControllerException;
import me.dokollari.college.manager.mvc.Controller;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class Course implements Serializable {
    @SuppressWarnings("compatibility:2414272007692748558")
    private static final long serialVersionUID = 7654000207789267859L;
    private String courseTitle;
    private int instructorID;
    private int studentLimit;
    public HashMap<Integer, me.dokollari.college.manager.models.Student> students;


    public Course(String course_title, int student_limit, int instructor_id) {
        this.courseTitle = course_title;
        this.studentLimit = student_limit;
        this.instructorID = instructor_id;
        students = new HashMap<Integer, me.dokollari.college.manager.models.Student>();
    }

   

    public boolean registerStudent(int newStudentID, me.dokollari.college.manager.models.Student newStudent) throws ControllerException {
        if (students.containsKey(newStudentID))
            throw new ControllerException(Controller.duplicateData(newStudent));
        else
            students.put(newStudentID, newStudent);
        return true;
    }

    public boolean removeStudent(String newStudentID) {
        if (students.containsKey(newStudentID)) {
            students.remove(newStudentID);
            return true;
        }

        return false;
    }

    public String displayClasslist() {
        String list = "";
        for (Map.Entry<Integer, me.dokollari.college.manager.models.Student> e : students.entrySet())
            list += e.getValue() + "\n";
        return list;
    }

    public String toString() {
        return String.format("%-20s\tLimit:%-5d", courseTitle, studentLimit);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof me.dokollari.college.manager.models.Course)) {
            return false;
        }
        final me.dokollari.college.manager.models.Course other = (me.dokollari.college.manager.models.Course)object;
        if (!(courseTitle == null ? other.courseTitle == null : courseTitle.equals(other.courseTitle))) {
            return false;
        }
        return true;
    }


    public String getCourseTitle() {
        return courseTitle;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

}
