package me.dokollari.college.manager.models;

import java.io.Serializable;

 /** @author Rizart Dokollari
 * @since November, 2012
 */

public class Student implements Serializable {
    private int studentID = 0;
    private String lastName;
    private String firstName;
    private int advisorID;//advisor is also an instrucotr, thus having the same id

    public Student(int ID, String lastname, String firstname) {
        this.studentID = ID;
        this.lastName = lastname;
        this.firstName = firstname;
    }


    public void setStudent_id(Integer ID) {
        this.studentID = ID;
    }

    public Integer getId() {
        return studentID;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setAdvisor(int advisor) {
        this.advisorID = advisor;
    }

    public int getAdvisor() {
        return advisorID;
    }

    public String toString() {
        return String.format("%-6s\t%-20s %-10s", studentID, lastName, firstName);

    }

    public boolean equals(me.dokollari.college.manager.models.Student x) {
        if (x.studentID == this.studentID)
            return true;
        else
            return false;

    }


    /*  @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ID;
        return result;
    } */


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof me.dokollari.college.manager.models.Student)) {
            return false;
        }
        final me.dokollari.college.manager.models.Student other = (me.dokollari.college.manager.models.Student) object;
        if (!(lastName == null ? other.lastName == null : lastName.equals(other.lastName))) {
            return false;
        }
        if (!(firstName == null ? other.firstName == null : firstName.equals(other.firstName))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = PRIME * result + ((firstName == null) ? 0 : firstName.hashCode());
        return result;
    }
}
