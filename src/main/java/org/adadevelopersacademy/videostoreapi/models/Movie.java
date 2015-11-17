package org.adadevelopersacademy.videostoreapi.models;

public class Movie {
    private final int id;
    private String title;
    private String overview;
    private String releaseDate;
    private int inventory;

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

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getInventory() {
        return inventory;
    }
}
