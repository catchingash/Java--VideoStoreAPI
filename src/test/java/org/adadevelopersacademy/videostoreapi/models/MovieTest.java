package org.adadevelopersacademy.videostoreapi.models;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest {
    private final Movie movie = new Movie.MovieBuilder()
            .id(1)
            .title("Title")
            .overview("Overview")
            .releaseDate("Release Date")
            .inventory(12)
            .build();
    @Test
    public void testTitleFieldIsPublic() {
        assertThat(movie.getTitle(), is("Title"));
    }

    @Test
    public void testOverviewFieldIsPublic() {
        assertThat(movie.getOverview(), is("Overview"));
    }

    @Test
    public void testReleaseDateFieldIsPublic() {
        assertThat(movie.getReleaseDate(), is("Release Date"));
    }

    @Test
    public void testInventoryFieldIsPublic() {
        assertThat(movie.getInventory(), is(12));
    }

//    @Test(expected = Exception.class)
//    public void testIdFieldIsPrivate() {
//        movie.id;
//    }
}
