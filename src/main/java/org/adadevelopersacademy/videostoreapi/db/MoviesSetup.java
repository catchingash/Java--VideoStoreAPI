package org.adadevelopersacademy.videostoreapi.db;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviesSetup {
    public static void dropTable() {
        System.out.println("Dropping movies table...");
        final String query = "DROP TABLE IF EXISTS movies";
        DB.executeUpdate(query, null, null);
    }

    public static void createTable() {
        System.out.println("Creating movies table...");
        final String query = "CREATE TABLE IF NOT EXISTS movies " +
                "(id SERIAL UNIQUE, " +
                "title TEXT, " +
                "overview TEXT, " +
                "releaseDate TEXT, " +
                "inventory INTEGER)";
        DB.executeUpdate(query, null, null);
    }

    public static void seedTable() {
        System.out.println("Seeding movies table...");
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final List<Map<String, Object>> movieList =
                    mapper.readValue(
                            new File("./src/main/java/org/adadevelopersacademy/videostoreapi/db/seeds/movies.json"),
                            List.class
                    );

            for (Map<String, Object> movieData : movieList) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("title", movieData.get("title"));
                params.put("overview", movieData.get("overview"));
                params.put("releaseDate", movieData.get("release_date"));
                params.put("inventory", movieData.get("inventory"));

                DatabaseUtilsForMovies.create(params);
            }
        } catch (Exception e) {
            // handle errors for Class.forName
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        dropTable();
        createTable();
        seedTable();
    }
}
