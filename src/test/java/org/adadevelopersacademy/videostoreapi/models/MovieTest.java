package org.adadevelopersacademy.videostoreapi.models;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest {
    private final Movie movie = new Movie(1, "Title", "Overview", "Release Date", 12);
    @Test
    public void testTitleFieldIsPublic() {
        assertThat(movie.title, is("Title"));
    }

    @Test
    public void testOverviewFieldIsPublic() {
        assertThat(movie.overview, is("Overview"));
    }

    @Test
    public void testReleaseDateFieldIsPublic() {
        assertThat(movie.releaseDate, is("Release Date"));
    }

    @Test
    public void testInventoryFieldIsPublic() {
        assertThat(movie.inventory, is(12));
    }

//    @Test(expected = Exception.class)
//    public void testIdFieldIsPrivate() {
//        movie.id;
//    }
}
