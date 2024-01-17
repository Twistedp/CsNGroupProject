package com.csn.spring_boot_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class handles all requests to /random.
 * Used for return a String with a random number.
 */

@RestController
public class RandomController {

    /**
     * Generates a string with a random number.
     *
     * @return a string with a random number.
     */

    @RequestMapping("random")
    public String random() {
        return "Your number is: " + ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }
}
