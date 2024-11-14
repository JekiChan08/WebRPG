package com.example.WebRPG.controller;


import com.example.WebRPG.Model.User;
import com.example.WebRPG.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/register/")
    public ResponseEntity<User> registration(@RequestBody User myUser) {
        User savedUser = userService.saveUser(myUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
