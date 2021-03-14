package com.awesome.testing.controller;

import com.awesome.testing.dto.LoginDto;
import com.awesome.testing.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@SuppressWarnings("ConstantConditions")
public abstract class DomainHelper extends HttpHelper {

    @BeforeEach
    public void setUp() {
        deleteAllUsers();
    }

    private void deleteAllUsers() {
        Arrays.stream(getAllUsers().getBody())
                .map(User::getId)
                .forEach(id -> executeDelete("/users/" + id));
    }

    protected <T> ResponseEntity<T> registerUser(User user, Class<T> clazz) {
        return executePost("/users/register", user, clazz);
    }

    protected <T> ResponseEntity<T> loginUser(LoginDto loginDto, Class<T> clazz) {
        return executePost("/users/authenticate", loginDto, clazz);
    }

    protected void registerUser(User user) {
        registerUser(user, Object.class);
    }

    protected ResponseEntity<User[]> getAllUsers() {
        return executeGet("/users", User[].class);
    }

    protected ResponseEntity<User> getUserById(long id) {
        return executeGet("/users/" + id, User.class);
    }

}
