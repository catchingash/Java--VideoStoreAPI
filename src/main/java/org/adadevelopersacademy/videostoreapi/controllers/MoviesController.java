package org.adadevelopersacademy.videostoreapi.controllers;

import org.adadevelopersacademy.videostoreapi.db.MoviesDB;
import org.adadevelopersacademy.videostoreapi.models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/movies")
public class MoviesController {
    @RequestMapping("/{title}")
    public @ResponseBody Movie show(
            @PathVariable final String title) {
        // returns an empty(?) response if movie was not found
        return MoviesDB.findBy("title", title);
    }

    @RequestMapping("/index")
    public @ResponseBody List<Movie> index() {
        return MoviesDB.all();
    }
}
