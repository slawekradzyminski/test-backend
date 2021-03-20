package com.awesome.testing.controller;

import com.awesome.testing.dto.ErrorDto;
import com.awesome.testing.dto.User;
import com.awesome.testing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping(value = "users/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.userAlreadyExists(user.getUsername())) {
            return ResponseEntity.badRequest().body(new ErrorDto("User already exists"));
        }

        User saved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
