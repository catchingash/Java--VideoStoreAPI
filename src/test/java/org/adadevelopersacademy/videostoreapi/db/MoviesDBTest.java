package org.adadevelopersacademy.videostoreapi.db;

import org.adadevelopersacademy.videostoreapi.models.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.sql.rowset.CachedRowSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MoviesDBTest {
    private final String title = "IAmTitle";
    private final String overview = "IAmOverview";
    private final String releaseDate = "IAmReleaseDate";
    private final int inventory = 12;

    // QUESTION: is this acceptable? Is this the way to do this?
    public Map movieParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title", title);
        params.put("overview", overview);
        params.put("releaseDate", releaseDate);
        params.put("inventory", inventory);
        return params;
    }

    // FIXME: obviously this is NOT ideal!
    public void truncateMovies() {
        DB.executeUpdate("TRUNCATE movies RESTART IDENTITY", null, null);
    }

    @Before
    @After
    public void setup() {
        truncateMovies();
    }

    @Test
    @Rollback // FIXME: this isn't doing what I'd hoped. Why?
    public void testCreateAddsNewRecord() {
        MoviesDB.create(movieParams());
        CachedRowSet movies = DB.executeQuery("SELECT * from movies", null, null);
        assertThat(movies.size(), is(1));
    }

    @Test
    @Rollback
    public void testFindByIDReturnsMovie() {
        MoviesDB.create(movieParams());
        assertThat(MoviesDB.findBy("id", 1), instanceOf(Movie.class));
        assertThat(MoviesDB.findBy("id", 1).getTitle(), is(title));
    }

    @Test
    @Rollback
    public void testFindByIdReturnsNullIfCantFindMovie() {
        assertNull(MoviesDB.findBy("id", 1));
    }

    @Test
    @Rollback
    public void testFindByTitleReturnsMovie() {
        MoviesDB.create(movieParams());
        assertThat(MoviesDB.findBy("title", title), instanceOf(Movie.class));
        assertThat(MoviesDB.findBy("title", title).getTitle(), is(title));
    }

    @Test
    @Rollback
    public void testFindByTitleReturnsNullIfCantFindMovie() {
        assertNull(MoviesDB.findBy("title", "IAmNotATitle"));
    }

    @Test
    @Rollback
    public void testAllReturnsAllMovies() {
        int count = 3;

        for (int i = 0; i < count; i++) {
            MoviesDB.create(movieParams());
        }

        assertThat(MoviesDB.all().size(), is(count));
        assertThat(MoviesDB.all().get(0), instanceOf(Movie.class));
    }

    @Test
    @Rollback
    public void testAllReturnsEmptyListIfNoMovies() {
        assertThat(MoviesDB.all(), instanceOf(List.class));
        assertThat(MoviesDB.all().size(), is(0));
    }
}
