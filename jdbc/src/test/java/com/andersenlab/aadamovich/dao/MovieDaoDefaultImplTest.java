package com.andersenlab.aadamovich.dao;

import com.andersenlab.aadamovich.dao.impl.MovieDaoDefaultImpl;
import com.andersenlab.aadamovich.model.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDaoDefaultImplTest {

    private BaseMovieDao dao = MovieDaoDefaultImpl.getInstance();

    @Test
    void getInstance() {
        assertNotNull(dao);
    }

    @Test
    void add() {
        final Movie testMovie = new Movie("NameAdd", "GenreAdd", true);
        final Integer primaryKey = dao.add(testMovie);
        assertNotNull(primaryKey);

        final Movie savedMovie = dao.findById(primaryKey);
        assertEquals(primaryKey, savedMovie.getId());
        assertEquals(testMovie.getName(), savedMovie.getName());
        assertEquals(testMovie.getGenre(), savedMovie.getGenre());
        assertEquals(testMovie.isCool(), savedMovie.isCool());
    }

    @Test
    void findById() {
        final Movie testMovie = new Movie("NameFind", "GenreFind", true);
        final Integer primaryKey = dao.add(testMovie);

        final Movie savedMovie = dao.findById(primaryKey);
        assertNotNull(savedMovie);
        assertEquals(primaryKey, savedMovie.getId());
        assertEquals(testMovie.getName(), savedMovie.getName());
        assertEquals(testMovie.getGenre(), savedMovie.getGenre());
        assertEquals(testMovie.isCool(), savedMovie.isCool());
    }

    @Test
    void update() {
        final Movie beforeUpdate = new Movie("NameUpdate", "GenreUpdate", false);
        final Integer primaryKey = dao.add(beforeUpdate);

        final Movie movieToUpdate = new Movie("Updated", "Updated", true);
        movieToUpdate.setId(primaryKey);

        final boolean isUpdated = dao.update(movieToUpdate);
        assertTrue(isUpdated);

        final Movie movieAfterUpdate = dao.findById(primaryKey);
        assertEquals(movieToUpdate, movieAfterUpdate);
    }

    @Test
    void delete() {
        final Movie beforeUpdate = new Movie("NameDelete", "GenreDelete", false);
        final Integer primaryKey = dao.add(beforeUpdate);

        final Movie movieToDelete = dao.findById(primaryKey);
        assertNotNull(movieToDelete);

        final boolean isDeleted = dao.delete(primaryKey);
        assertTrue(isDeleted);

        final Movie movieAfterDelete = dao.findById(primaryKey);
        assertNull(movieAfterDelete);
    }
}
