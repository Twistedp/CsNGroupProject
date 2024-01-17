package com.csn.spring_boot_project.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class holds the model for the database.
 */

@Data
@Document("Movies")
public class Movie {

    private String name;
    private int year;
    private int length;

    public Movie(String name, Integer year, Integer length) {
        this.name = name;
        this.year = year;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Year: " + year + ", Length: " + length;
    }
}
