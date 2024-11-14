package com.example.WebRPG.controller;

import com.example.WebRPG.Model.User;
import com.example.WebRPG.Service.UserService;
import com.example.WebRPG.controller.Dao.UserDao;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model){
        model.addAttribute("user", new UserDao());
        return "registration-form";
    }
    @PostMapping("/registration/save")
    public String register(@Valid @ModelAttribute("user") UserDao userDao, Model model, BindingResult result){
        User existingUser = service.findByLogin(userDao.getLogin());
        if (existingUser != null){
            result.reject("Login already exist");
        }
        if (result.hasErrors()){
            model.addAttribute("user", userDao);
            return "registration-form";
        }
        service.saveUser(new User(userDao.getLogin(), userDao.getPassword()));
        return "redirect:/registration?success";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
