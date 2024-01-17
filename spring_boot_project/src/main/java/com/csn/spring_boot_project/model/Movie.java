package com.csn.spring_boot_project.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("movie")
public class Movie {

    private String name;
    private int year;
    private int length;

    @Override
    public String toString() {
        return "Name: " + name + ", Year: " + year + ", Length: " + length;
    }
}
