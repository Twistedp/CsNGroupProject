package com.csn.spring_boot_project.controller;

import com.csn.spring_boot_project.model.Movie;
import com.csn.spring_boot_project.repository.MovieRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class handles all requests to /movie.
 * Used for return all movies and adding new ones.
 */
@RestController
public class MovieController {

    final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }


    /**
     * Returns all movies, which are stored in the database.
     *
     * @return movies in database. Not sorted.
     */

    @RequestMapping("movie")
    public String movies() {
        return "Movies: \n" + repository.findAll();
    }

    /**
     * Used to add a movie into the database with url parameter.
     *
     * @param name name of the movie
     * @param year year of the movie
     * @param length length of the movie in minutes.
     */

    @RequestMapping("movie/add")
    public void addMovie(@RequestParam String name,
                         @RequestParam Integer year,
                         @RequestParam Integer length) {
        repository.insert(new Movie(name, year, length));
    }
}
