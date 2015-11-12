package org.adadevelopersacademy.videostoreapi.controllers;

import org.adadevelopersacademy.videostoreapi.db.DatabaseUtilsForMovies;
import org.adadevelopersacademy.videostoreapi.models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/movies")
public class MoviesController {
    @RequestMapping("/{title}")
    public @ResponseBody Movie show(
            @PathVariable String title
    ) {
        Movie movie = DatabaseUtilsForMovies.findByTitle(title);
        return movie; // sends back an empty(?) response if movie was not found
    }
}
