package com.csn.spring_boot_project.controller;

import com.csn.spring_boot_project.model.User;
import com.csn.spring_boot_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private Pbkdf2PasswordEncoder encoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    @GetMapping(path = "/register")
    private String registerUser() {
        return "register";
    }

    @GetMapping(path="/add")
    public ModelAndView addNewUser (@RequestParam String name,
                                    @RequestParam String password) {

        User n = new User();
        n.setName(name);
        n.setPassword(password);
        n.setNumber(ThreadLocalRandom.current().nextInt(0, 101));
        userRepository.save(n);
        return new ModelAndView("redirect:/demo/all");
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
