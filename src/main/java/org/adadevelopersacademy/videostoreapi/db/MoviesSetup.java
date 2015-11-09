package org.adadevelopersacademy.videostoreapi.db;

//  Establish a connection to a Postgres database using JDBC
import java.sql.*;

public class MoviesSetup {
    public static void dropTable() {
        String query = "DROP TABLE IF EXISTS movies";
        executeQuery(query);
    }

    public static void createTable() {
        String query =
            "CREATE TABLE IF NOT EXISTS movies " +
            "(id SERIAL UNIQUE, " +
            "title TEXT, " +
            "overview TEXT, " +
            "release_date TEXT, " +
            "inventory INTEGER)";
        executeQuery(query);
    }

    public static void seedTable() {
        // FIXME: Need to re-write so that it'll seed from the JSON file.
        String query =
            "INSERT INTO movies (title) " +
            "VALUES ('Woof')";
        executeQuery(query);
    }

    // TODO: probably better to move this into a separate class? Re-evaluate later.
    public static void executeQuery(String query) {
        Statement stmt = null;
        Connection conn = null;
        try {
            // load JDBC Postgresql driver
            Class.forName("org.postgresql.Driver");

            // establish connection to the database
            String url = "jdbc:postgresql:java_videoapi_dev";
            conn = DriverManager.getConnection(url, "java_videoapi", "SuperSecurePassword01");

            // execute query
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            // handle errors for Class.forName
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if (stmt != null) { conn.close(); }
                if (conn != null) { conn.close(); }
            } catch(SQLException se) {
                // handle errors for closing db connection
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        dropTable();
        createTable();
        seedTable();
    }
}
