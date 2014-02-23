package me.dokollari.college.manager.models;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import me.dokollari.college.manager.mvc.Controller;
import me.dokollari.college.manager.mvc.ControllerException;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class Course implements Serializable {
    @SuppressWarnings("compatibility:5125067953816511869")
    private static final long serialVersionUID = 7654000207789267859L;
    private String courseTitle;
    private int instructorID;
    private int studentLimit;
    private HashMap<Integer, me.dokollari.college.manager.models.Student> students;


    public Course(String course_title, int student_limit, int instructor_id) {
        this.courseTitle = course_title;
        this.studentLimit = student_limit;
        this.instructorID = instructor_id;
        students = new HashMap<Integer, me.dokollari.college.manager.models.Student>();
    }


    public boolean registerStudent(Student newStudent) throws ControllerException {
        int id = newStudent.getId();
        if (students.containsKey(id)) {
            throw new ControllerException(Controller.duplicateData(newStudent));
        } else {
            students.put(id, newStudent);
        }
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
        final me.dokollari.college.manager.models.Course other = (me.dokollari.college.manager.models.Course) object;
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

    public HashMap<Integer, Student> getStudents() {
        return students;
    }
}
