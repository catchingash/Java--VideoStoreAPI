package org.adadevelopersacademy.videostoreapi.models;

public class Movie {
    // public fields will be displayed in returned JSON
    private final int id;
    public String title;
    public String overview;
    public String releaseDate;
    public int inventory;

    public Movie(
            int id,
            String title,
            String overview,
            String releaseDate,
            int inventory
    ) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.inventory = inventory;
    }
}
