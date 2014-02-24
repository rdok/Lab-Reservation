package me.dokollari.college.manager.models;

import java.io.Serializable;


/** @author Rizart Dokollari
 * @since November, 2012
 */

public class Instructor  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String lastName;
    private String firstName;
    private int instructorID;
    
    public Instructor(String last_name, String first_name, int instructor_id) {
        this.lastName = last_name;
        this.firstName = first_name;
        this.instructorID = instructor_id;
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
    
    public String toString () {
        return String.format("Prof. %-13s  %-13s %7d",lastName ,firstName, instructorID);
        }


    public int getInstructorID() {
        return instructorID;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof me.dokollari.college.manager.models.Instructor)) {
            return false;
        }
        final me.dokollari.college.manager.models.Instructor other = (me.dokollari.college.manager.models.Instructor) object;
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
