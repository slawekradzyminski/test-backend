package com.awesome.testing.controller;

import com.awesome.testing.dto.LoginDto;
import com.awesome.testing.dto.LoginResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @SuppressWarnings("unused")
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(new LoginResponseDto("email", "token"));
    }

}
