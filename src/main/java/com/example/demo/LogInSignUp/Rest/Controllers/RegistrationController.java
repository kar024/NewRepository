package com.example.demo.LogInSignUp.Rest.Controllers;

import com.example.demo.LogInSignUp.Rest.Models.UserRequestModel;
import com.example.demo.LogInSignUp.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);


    @PostMapping(value = "/registration")
    public void addUser(@RequestBody UserRequestModel userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.info("Something went wrong during registration,try again");
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            LOGGER.info("password error: wrong password");
        }
        if (!userService.saveUser(userForm)) {
            LOGGER.info("username error: user with that name already exists");
        }
        userService.saveUser(userForm);
    }
}

