package com.csn.spring_boot_project.repository;

import com.csn.spring_boot_project.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Implements all functionality for the repository for the MongoDB database.
 * Features necessary functions
 */

public interface MovieRepository extends MongoRepository<Movie, String> {

    @Query("{name:'?0'}")
    Movie findMovieByName(String name);

    long count();
}
