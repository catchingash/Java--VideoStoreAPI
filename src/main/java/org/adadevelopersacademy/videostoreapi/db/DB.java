package org.adadevelopersacademy.videostoreapi.db;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

//  Establish a connection to a Postgres database using JDBC
public class DB {
    public static void executeUpdate(
            @NotNull final String query
    ) {
        Statement stmt = null;
        Connection conn = null;
        try {
            // load JDBC Postgresql driver
            Class.forName("org.postgresql.Driver");

            // establish connection to the database
            final String url = "jdbc:postgresql:java_videoapi_dev";
            conn = DriverManager.getConnection(url, "java_videoapi", "SuperSecurePassword01");

            // execute query
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (final Exception e) {
            // handle errors for Class.forName
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                // handle errors for closing db connection
                se.printStackTrace();
            }
        }
    }

    public static CachedRowSet executeQuery(
            @NotNull final String query,
            @Nullable Connection conn
    ) {
        Statement stmt = null;
        ResultSet res = null;
        CachedRowSet rows = null;

        try {
            // load JDBC Postgresql driver
            // FIXME: does this need to be inside the `if (conn == null)` block?
            Class.forName("org.postgresql.Driver");

            if (conn == null) {
                // establish connection to the database
                conn = DriverManager.getConnection(
                        "jdbc:postgresql:java_videoapi_dev",
                        "java_videoapi",
                        "SuperSecurePassword01");
            }

            // execute query
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);
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
