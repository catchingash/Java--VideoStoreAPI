package org.adadevelopersacademy.videostoreapi.db;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.List;

//  Establish a connection to a Postgres database using JDBC
public class DB {
    public static boolean executeUpdate(
            final String query,
            final List<Object> params,
            Connection conn
    ) {
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            // load JDBC Postgresql driver
            // FIXME: does this need to be inside the `if (conn == null)` block?
            Class.forName("org.postgresql.Driver");

            // NOTE: allowing conn to be passed in for testing purposes
            if (conn == null) {
                // establish connection to the database
                conn = DriverManager.getConnection(
                        "jdbc:postgresql:java_videoapi_dev",
                        "java_videoapi",
                        "SuperSecurePassword01");
            }


            // execute query
            stmt = conn.prepareStatement(query);
            if (params != null) {
                int i = 1;
                for (Object param : params) {
                    stmt.setObject(i, param);
                    i++;
                }
            }
            stmt.executeUpdate();
            success = true;
        } catch (final Exception e) {
            // handle errors for Class.forName
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            System.err.println("Query: ");
            System.err.println(query);
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                // handle errors for closing db connection
                se.printStackTrace();
            }
        }

        return success;
    }

    public static CachedRowSet executeQuery(
            final String query,
            final List<Object> params,
            Connection conn
    ) {
        PreparedStatement stmt = null;
        ResultSet res = null;
        CachedRowSet rows = null;

        try {
            // load JDBC Postgresql driver
            // FIXME: does this need to be inside the `if (conn == null)` block?
            Class.forName("org.postgresql.Driver");

            // NOTE: allowing conn to be passed in for testing purposes
            if (conn == null) {
                // establish connection to the database
                conn = DriverManager.getConnection(
                        "jdbc:postgresql:java_videoapi_dev",
                        "java_videoapi",
                        "SuperSecurePassword01");
            }

            // execute query
            stmt = conn.prepareStatement(query);
            if (params != null) {
                int i = 1;
                for (Object param : params) {
                    stmt.setObject(i, param);
                    i++;
                }
            }
            res = stmt.executeQuery();
            rows = new CachedRowSetImpl();
            rows.populate(res);
        } catch (final Exception e) {
            // handle errors for Class.forName
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (res != null) {
                    res.close();
                }
            } catch (SQLException se) {
                // handle errors for closing db connection
                se.printStackTrace();
            }
        }

        return rows;
    }
}
