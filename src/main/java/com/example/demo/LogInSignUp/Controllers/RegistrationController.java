package com.example.demo.LogInSignUp.Controllers;

import com.example.demo.LogInSignUp.Services.UserService;
import com.example.demo.LogInSignUp.persistence.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);


    @GetMapping(value = "/registration")
    public String registration() {
        User user = new User();
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(@RequestBody User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            LOGGER.info("Something went vrong during registration,try again");
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            LOGGER.info("password error: wrong password");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            LOGGER.info("username error: user with that name already exists");
            return "registration";
        }

        return "redirect:/";
    }
}

