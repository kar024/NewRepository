package com.example.demo.LogInSignUp.Rest.Controllers;

import com.example.demo.LogInSignUp.Rest.Models.UserResponseModel;
import com.example.demo.LogInSignUp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public ResponseEntity<List<UserResponseModel>> userList() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @DeleteMapping("/admin")
    public void deleteUser(@RequestParam(required = true, defaultValue = "") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/admin/get/{userId}")
    public ResponseEntity<UserResponseModel> getUsers(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }
}
