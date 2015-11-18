package org.adadevelopersacademy.videostoreapi.controllers;

import org.junit.Test;

public class MoviesControllerTest {
    @Test
    public void iJustWantToSeeMyMovies() {
        MoviesController moviesController = new MoviesController();
        System.out.println(moviesController.index());
    }
}
