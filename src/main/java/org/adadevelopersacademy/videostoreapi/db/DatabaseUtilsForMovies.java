package org.adadevelopersacademy.videostoreapi.db;

import org.adadevelopersacademy.videostoreapi.models.Movie;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseUtilsForMovies {
    public static boolean create(
            final Map params
    ) {
        final String query = "INSERT INTO movies " +
                "(title, overview, releaseDate, inventory) " +
                "VALUES (?, ?, ?, ?)";
        List<Object> values = new ArrayList<Object>();
        values.add(params.get("title"));
        values.add(params.get("overview"));
        values.add(params.get("releaseDate"));
        values.add(params.get("inventory"));
        return DB.executeUpdate(query, values, null);
    }

    public static Movie findByID(
            final int id
    ) {
        Movie movie = null;
        final String query = "SELECT * FROM movies " +
                "WHERE id = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(id);
        final CachedRowSet res = DB.executeQuery(query, params, null);

        if (res != null && res.size() > 0) {
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
        Movie movie = null;
        final String query = "SELECT * FROM movies " +
                "WHERE title = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(title);
        final CachedRowSet res = DB.executeQuery(query, params, null);

        if (res != null && res.size() > 0) {
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

    public static List<Movie> all() {
        List<Movie> movies = new ArrayList<Movie>();
        final String query = "SELECT * FROM movies";
        final CachedRowSet res = DB.executeQuery(query, null, null);

        if (res != null && res.size() > 0) {
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
}
