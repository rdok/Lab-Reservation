package me.dokollari.college.manager.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

import javax.swing.JProgressBar;

public class DB {
    private final static String DB_CONNECTION = "jdbc:mysql://db4free.net:3306/labsdb";
    private final static String DB_USER = "labsadmin";
    private final static String DB_PASSWORD = "fxWI7MSvqKcEo1lHg8v7";
    private Connection connection;
    private JProgressBar jPB_db;

    public DB(JProgressBar jPB_db) throws SQLException {
        setJPB_db(jPB_db);
        setConnection(establishDBConnection());
    }

    /**
     * source:
     * http://www.mkyong.com/jdbc/jdbc-preparestatement-example-select-list
     * -of-the-records/
     *
     * @return
     */
    private Connection establishDBConnection() throws SQLException {
        jPB_db.setValue(jPB_db.getValue());
        jPB_db.setString(Integer.toString(jPB_db.getValue()) + "% Establishing database connection");

        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", DB_USER);
        connectionProps.put("password", DB_PASSWORD);
        connection = DriverManager.getConnection(DB_CONNECTION, connectionProps);

        jPB_db.setValue(jPB_db.getValue() + 8);
        jPB_db.setString(Integer.toString(jPB_db.getValue()) + "% Database connection established");

        return connection;
    }

    ResultSet getStudents() throws SQLException {
        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieving Student Data");

        Statement statement_insertStudents = getConnection().createStatement();
        ResultSet resultSet = statement_insertStudents.executeQuery(DB_queries.SELECT_STUDENTS);

        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieved Student Data");
        
        return resultSet;
    }

    ResultSet getCourses() throws SQLException {
        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieving Courses Data");
        
        Statement coursesStatement = getConnection().createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_COURSES);

        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieved Courses Data");
        return resultSet;
    }

    ResultSet getInstructors() throws SQLException {
        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieving Instructor Data");
        
        Connection myConnection = getConnection();
        Statement coursesStatement = myConnection.createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_INSTRUCTORS);

        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieving Instructor Data");
        return resultSet;
    }

    ResultSet getRooms() throws SQLException {
        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieving Rooms Data");
        
        
        Connection myConnection = getConnection();
        Statement coursesStatement = myConnection.createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_ROOMS);

        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieved Instructor Data");
        return resultSet;
    }

    ResultSet getReservedRooms() throws SQLException {
        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieving Reserved Rooms Data");
        
        Connection myConnection = getConnection();
        Statement coursesStatement = myConnection.createStatement();
        ResultSet resultSet = coursesStatement.executeQuery(DB_queries.SELECT_RESERVED_ROOMS);
        
        getJPB_db().setValue(jPB_db.getValue() + 8);
        getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Retrieved Reserved Rooms Data");
        return resultSet;
    } // end method getReservedRooms

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() throws SQLException {

        // verify db connection
        if (connection.isClosed()) {
            getJPB_db().setString(Integer.toString(jPB_db.getValue()) + "% Re-establishing database connection");
            setConnection(establishDBConnection());
        } // end if
        return connection;
    }

    public void setJPB_db(JProgressBar jPB_db) {
        this.jPB_db = jPB_db;
    }

    public JProgressBar getJPB_db() {
        return jPB_db;
    }
} // end DB class
