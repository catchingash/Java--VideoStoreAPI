package org.adadevelopersacademy.videostoreapi.db;

public class MoviesSetup {
    public static void dropTable() {
        final String query = "DROP TABLE IF EXISTS movies";
        System.out.println("Dropping movies table...");
        DB.executeUpdate(query);
    }

    public static void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS movies " +
                "(id SERIAL UNIQUE, " +
                "title TEXT, " +
                "overview TEXT, " +
                "releaseDate TEXT, " +
                "inventory INTEGER)";
        System.out.println("Creating movies table...");
        DB.executeUpdate(query);
    }

    public static void seedTable() {
        // FIXME: Need to re-write so that it'll seed from the JSON file.
        final String query = "INSERT INTO movies (title, overview, releaseDate, inventory) " +
                "VALUES ('Woof', 'A movie about a dog', 'Nov 24, 2015', 5)";
        System.out.println("Seeding movies table...");
        DB.executeUpdate(query);
    }

    public static void main(String[] args) {
        dropTable();
        createTable();
        seedTable();
    }
}
