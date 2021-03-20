package com.awesome.testing.controller;

import com.awesome.testing.dto.LoginDto;
import com.awesome.testing.dto.ErrorDto;
import com.awesome.testing.dto.LoginResponseDto;
import com.awesome.testing.dto.User;
import com.awesome.testing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping(value = "users/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AuthenticateController {

    private final UserService userService;

    public AuthenticateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginCredentials) {
        log.info("POST - attempt to log in user with username {}", loginCredentials.getUsername());
        Optional<User> optionalUser = userService.authenticate(loginCredentials);
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(buildResponse(optionalUser.get()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("Login failed - bad username or password"));
    }

    private LoginResponseDto buildResponse(User user) {
        return LoginResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .token("123456")
                .build();
    }

}
