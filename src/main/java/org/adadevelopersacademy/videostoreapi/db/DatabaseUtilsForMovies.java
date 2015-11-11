package org.adadevelopersacademy.videostoreapi.db;

import com.sun.istack.internal.NotNull;
import org.adadevelopersacademy.videostoreapi.models.Movie;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtilsForMovies {
    public static Movie findByID(
            @NotNull final int id
    ) {
        final CachedRowSet res;
        Movie movie = null;
        final String query = "SELECT movies.* FROM movies " +
                "WHERE movies.id = " + id;
        res = DB.executeQuery(query, null);

        if (res != null) {
            try {
                res.next();
                movie = new Movie(
                        res.getInt("id"),
                        res.getString("title"),
                        res.getString("overview"),
                        res.getString("releaseDate"),
                        res.getInt("inventory")
                );
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }

        return movie;
    }

    public static Movie findByTitle(
            final String title
    ) {
        final CachedRowSet res;
        Movie movie = null;
        final String query = "SELECT movies.* FROM movies " +
                "WHERE TITLE = '" + title + "'";
        res = DB.executeQuery(query, null);

        if (res != null) {
            try {
                res.next();
                movie = new Movie(
                        res.getInt("id"),
                        res.getString("title"),
                        res.getString("overview"),
                        res.getString("releaseDate"),
                        res.getInt("inventory")
                );
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }

        return movie;
    }

    // QUESTION: MOVE INTO THE CONTROLLER??
    public static List<Movie> all() {
        List<Movie> movies = new ArrayList<Movie>();
        String query = "SELECT movies.* FROM movies";
        CachedRowSet res = DB.executeQuery(query, null);

        if (res != null) {
            try {
                while (res.next()) {
                    Movie movie = new Movie(
                            res.getInt("id"),
                            res.getString("title"),
                            res.getString("overview"),
                            res.getString("releaseDate"),
                            res.getInt("inventory")
                    );

                    movies.add(movie);
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public static void main(
            final String[] args
    ) {
        System.out.println(findByID(1));
        System.out.println(findByTitle("Woof"));
        System.out.println(all());
    }
}
