package com.awesome.testing.controller;

import com.awesome.testing.dto.LoginDto;
import com.awesome.testing.dto.ErrorDto;
import com.awesome.testing.dto.LoginResponseDto;
import com.awesome.testing.dto.User;
import com.awesome.testing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping(value = "users/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticateController {

    private final UserService userService;

    public AuthenticateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginCredentials) {
        Optional<User> optionalUser = userService.authenticate(loginCredentials);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .username(user.getUsername())
                    .token("123456")
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("Login failed - bad username or password"));
    }

}
