package com.example.demo.LogInSignUp.Controllers;

import com.example.demo.LogInSignUp.Services.UserService;
import com.example.demo.LogInSignUp.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public ResponseEntity<List<User>> userList() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @DeleteMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/get/{userId}")
    public ResponseEntity<List<User>> getUsers(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.userGetList(userId));
    }
}
