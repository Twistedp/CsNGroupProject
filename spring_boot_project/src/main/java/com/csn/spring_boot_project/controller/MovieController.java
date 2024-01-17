package com.csn.spring_boot_project.controller;

import com.csn.spring_boot_project.repository.MovieRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("movie")
    public String movieAmount() {
        return "Movies: \n" + repository.findAll();
    }
}
