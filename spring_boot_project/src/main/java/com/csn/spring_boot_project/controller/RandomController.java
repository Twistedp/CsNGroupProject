package com.csn.spring_boot_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RandomController {

    @RequestMapping("random")
    public String random() {
        return "Your number is: " + ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }
}
