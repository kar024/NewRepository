package com.example.demo.LogInSignUp.Controllers;

import com.example.demo.LogInSignUp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @DeleteMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             Model model) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }
    @GetMapping("/admin/get/{userId}")
    public String  getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.userGetList(userId));
        return "admin";
    }
}
