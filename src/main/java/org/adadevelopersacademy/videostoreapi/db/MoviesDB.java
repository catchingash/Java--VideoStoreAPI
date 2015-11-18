package org.adadevelopersacademy.videostoreapi.db;

import org.adadevelopersacademy.videostoreapi.models.Movie;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MoviesDB {
    static final String TABLE_NAME = "movies";
    static final List<String> COLUMNS = Arrays.asList("title", "overview", "releaseDate", "inventory");

    public static boolean create(
            final Map<String, Object> params) {
        return DB.createRow(TABLE_NAME, COLUMNS, params);
    }

    public static Movie findBy(
            final String param,
            final Object paramValue) {
        final CachedRowSet res = DB.findBy(TABLE_NAME, param, paramValue);
        return populateObjectInRes(res);
    }

    public static List<Movie> all() {
        final CachedRowSet res = DB.all(TABLE_NAME);
        return populateObjectsInRes(res);
    }

    private static Movie populateObjectInRes(
            final CachedRowSet res) {
        Movie movie = null;

        if (res != null && res.size() > 0) {
            try {
                res.next();
                movie = createMovieFromRecord(res);
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }

        return movie;
    }

    private static List<Movie> populateObjectsInRes(
            final CachedRowSet res) {
        final List<Movie> movies = new ArrayList<Movie>();

        if (res != null && res.size() > 0) {
            try {
                while (res.next()) {
                    movies.add(createMovieFromRecord(res));
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }

        return movies;
    }

    private static Movie createMovieFromRecord(
            final CachedRowSet row) throws SQLException {
        return Movie.getBuilder()
                .id(row.getInt("id"))
                .title(row.getString("title"))
                .overview(row.getString("overview"))
                .releaseDate(row.getString("releaseDate"))
                .inventory(row.getInt("inventory"))
                .build();
    }
}
