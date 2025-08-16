package com.homebakertf.home_baker.controller;

import com.homebakertf.home_baker.model.User;
import com.homebakertf.home_baker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // allow React frontend
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Create user (register)
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Login (basic check by username & password)
    @PostMapping("/login")
    public String login(@RequestBody User loginData) {
        Optional<User> userOpt = userService.getByUsername(loginData.getUsername());
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            if(user.getPasswordHash().equals(loginData.getPasswordHash())) {
                return "Login successful";
            } else {
                return "Invalid password";
            }
        }
        return "User not found";
    }

    // Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
