package com.inn.cafe.controller;

import com.inn.cafe.model.User;
import com.inn.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    private UserService userService;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, String> requestMap) {
        return service.signUp(requestMap);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return service.getByUserId(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id,
                           @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
        return "User deleted successfully";
    }
}