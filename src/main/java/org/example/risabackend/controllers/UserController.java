package org.example.risabackend.controllers;

import org.example.risabackend.models.User;
import org.example.risabackend.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> index() {
        List<User> users = userRepository.findAll();
        users.forEach(e -> System.out.println(e.getFullName()));
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/user")
    User addUser(@RequestBody User user) {
        System.out.println("user sout");
        System.out.println(user);
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setFullName(user.getFullName());
                    u.setEmail(user.getEmail());
                    return userRepository.save(u);
                }).orElseGet(() -> {
                    return userRepository.save(user);
                });
    }

}
