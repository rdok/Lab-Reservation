package me.dokollari.college.manager.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

import me.dokollari.college.manager.swings.FrameMainLabReservation;

public class DB {
    private final static String DB_CONNECTION = "jdbc:mysql://db4free.net:3306/labsdb";
    private final static String DB_USER = "labsadmin";
    private final static String DB_PASSWORD = "fxWI7MSvqKcEo1lHg8v7";

    public static void main(String[] args) {
        new me.dokollari.college.manager.mvc.DB();
    }

    public DB() {
        //        try {
        //            readDatabase();
        //        } catch (Exception e) {
        //            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        //        }
    }

    public void readDatabase() throws Exception {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String selectSQL = "SELECT * FROM patients";


        connection = getDBConnection();
        preparedStatement = connection.prepareStatement(selectSQL);
        // preparedStatement.setInt(1, 1001);

        // execute select SQL statement
        resultSet = preparedStatement.executeQuery(selectSQL);

        while (resultSet.next()) {
            String patientId = resultSet.getString("id_app");
            String patientSurgery = resultSet.getString("surgery");

            System.out.printf("Patient id: %s\tPatient surgery: %s", patientId, patientSurgery);
        }
    }

    /**
     * source:
     * http://www.mkyong.com/jdbc/jdbc-preparestatement-example-select-list
     * -of-the-records/
     *
     * @return
     */
    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;

        Properties connectionProps = new Properties();
        connectionProps.put("user", DB_USER);
        connectionProps.put("password", DB_PASSWORD);
        connection = DriverManager.getConnection(DB_CONNECTION, connectionProps);
        return connection;
    }

    static ResultSet getStudents() throws SQLException {
        FrameMainLabReservation.setActivityMessagea("Retrieving Student Data");

        Connection myConnection;

        myConnection = getDBConnection();

        Statement statement_insertStudents = myConnection.createStatement();
        ResultSet resultSet = statement_insertStudents.executeQuery(DB_queries.SELECT_STUDENTS);

        FrameMainLabReservation.setActivityMessagea("Retrieved Student Data");
        return resultSet;
    }

    static ResultSet getCourses() throws SQLException {
        FrameMainLabReservation.setActivityMessagea("Retrieving Courses Data");

        Connection myConnection = getDBConnection();
        Statement coursesStatement = myConnection.createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_COURSES);

        FrameMainLabReservation.setActivityMessagea("Retrieved Courses Data");
        return resultSet;
    }

    static ResultSet getInstructors() throws SQLException {
        FrameMainLabReservation.setActivityMessagea("Retrieving Instructor Data");

        Connection myConnection = getDBConnection();
        Statement coursesStatement = myConnection.createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_COURSES);

        FrameMainLabReservation.setActivityMessagea("Retrieved Instructor Data");
        return resultSet;
    }

    static ResultSet getRooms() throws SQLException {
        FrameMainLabReservation.setActivityMessagea("Retrieving Rooms Data");

        Connection myConnection = getDBConnection();
        Statement coursesStatement = myConnection.createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_ROOMS);

        FrameMainLabReservation.setActivityMessagea("Retrieved Rooms Data");
        return resultSet;
    }

    static ResultSet getReservedRooms() throws SQLException {
        FrameMainLabReservation.setActivityMessagea("Retrieving Reserved Rooms Data");

        Connection myConnection = getDBConnection();
        Statement coursesStatement = myConnection.createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_RESERVED_ROOMS);

        FrameMainLabReservation.setActivityMessagea("Retrieved Reserved Rooms Data");
        return resultSet;
    }
}
