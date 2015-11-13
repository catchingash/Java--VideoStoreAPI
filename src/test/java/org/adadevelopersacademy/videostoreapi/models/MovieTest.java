package org.adadevelopersacademy.videostoreapi.models;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest {
    @Test
    public void testTitleFieldIsPublic() {
        Movie movie = new Movie(1, "Title", "Overview", "Release Date", 12);
        assertThat(movie.title, is("Title"));
    }

    @Test
    public void testOverviewFieldIsPublic() {
        Movie movie = new Movie(1, "Title", "Overview", "Release Date", 12);
        assertThat(movie.overview, is("Overview"));
    }

    @Test
    public void testReleaseDateFieldIsPublic() {
        Movie movie = new Movie(1, "Title", "Overview", "Release Date", 12);
        assertThat(movie.releaseDate, is("Release Date"));
    }

    @Test
    public void testInventoryFieldIsPublic() {
        Movie movie = new Movie(1, "Title", "Overview", "Release Date", 12);
        assertThat(movie.inventory, is(12));
    }

//    @Test(expected = Exception.class)
//    public void testIdFieldIsPrivate() {
//        Movie movie = new Movie(1, "Title", "Overview", "Release Date", 12);
//        movie.id;
//    }
}
