package org.adadevelopersacademy.videostoreapi.db;

import org.adadevelopersacademy.videostoreapi.models.Movie;
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

public class DatabaseUtilsForMoviesTest {
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

    @Test
    @Rollback // FIXME: this isn't doing what I'd hoped. Why?
    public void testCreateAddsNewRecord() {
        truncateMovies();
        DatabaseUtilsForMovies.create(movieParams());
        CachedRowSet movies = DB.executeQuery("SELECT * from movies", null, null);
        assertThat(movies.size(), is(1));
    }

    @Test
    @Rollback
    public void testFindByIdReturnsMovie() {
        truncateMovies();
        DatabaseUtilsForMovies.create(movieParams());
        assertThat(DatabaseUtilsForMovies.findByID(1), instanceOf(Movie.class));
        assertThat(DatabaseUtilsForMovies.findByID(1).title, is(title));
    }

    @Test
    @Rollback
    public void testFindByIdReturnsNullIfCantFindMovie() {
        truncateMovies();
        assertNull(DatabaseUtilsForMovies.findByID(1));
    }

    @Test
    @Rollback
    public void testFindByTitleReturnsMovie() {
        truncateMovies();
        DatabaseUtilsForMovies.create(movieParams());
        assertThat(DatabaseUtilsForMovies.findByTitle(title), instanceOf(Movie.class));
        assertThat(DatabaseUtilsForMovies.findByTitle(title).title, is(title));
    }

    @Test
    @Rollback
    public void testFindByTitleReturnsNullIfCantFindMovie() {
        truncateMovies();
        assertNull(DatabaseUtilsForMovies.findByTitle("IAmNotATitle"));
    }

    @Test
    @Rollback
    public void testAllReturnsAllMovies() {
        truncateMovies();
        int count = 3;

        for (int i = 0; i < count; i++) {
            DatabaseUtilsForMovies.create(movieParams());
        }

        assertThat(DatabaseUtilsForMovies.all().size(), is(count));
        assertThat(DatabaseUtilsForMovies.all().get(0), instanceOf(Movie.class));
    }

    @Test
    @Rollback
    public void testAllReturnsEmptyListIfNoMovies() {
        truncateMovies();
        assertThat(DatabaseUtilsForMovies.all(), instanceOf(List.class));
        assertThat(DatabaseUtilsForMovies.all().size(), is(0));
    }
}
