package org.adadevelopersacademy.videostoreapi.models;

public class Movie {
    private final int id;
    private final String title;
    private final String overview;
    private final String releaseDate;
    private final int inventory;

    private Movie(
            final int id,
            final String title,
            final String overview,
            final String releaseDate,
            final int inventory) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.inventory = inventory;
    }

    public static MovieBuilder getBuilder() {
        return new MovieBuilder();
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

    public static class MovieBuilder {
        private int id;
        private String title;
        private String overview;
        private String releaseDate;
        private int inventory;

        public MovieBuilder() {}

        public MovieBuilder id(final int id) {
            this.id = id;
            return this;
        }

        public MovieBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder overview(final String overview) {
            this.overview = overview;
            return this;
        }

        public MovieBuilder releaseDate(final String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieBuilder inventory(final int inventory) {
            this.inventory = inventory;
            return this;
        }

        public Movie build() {
            return new Movie(
                    this.id,
                    this.title,
                    this.overview,
                    this.releaseDate,
                    this.inventory);
        }
    }
}
