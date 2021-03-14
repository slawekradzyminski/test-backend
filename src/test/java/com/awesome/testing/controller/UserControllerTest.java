package com.awesome.testing.controller;

import com.awesome.testing.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ConstantConditions")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends HttpHelper {

    @BeforeEach
    public void setUp() {
        deleteAllUsers();
    }

    @Test
    public void shouldCreateUser() {
        User user = User.builder()
                .firstName("Slawek")
                .lastName("Radzyminski")
                .username("slawekradz")
                .password("password")
                .build();

        ResponseEntity<User> userCreated = executePost("/users", user, User.class);
        assertThat(userCreated.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        long createdUserId = userCreated.getBody().getId();

        ResponseEntity<User> userGotById = getUserById(createdUserId);
        assertThat(userGotById.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userGotById.getBody().getPassword()).isEqualTo("password");
    }

    @Test
    public void shouldUpdateUser() {
        User user = User.builder()
                .firstName("Slawek")
                .lastName("Radzyminski")
                .username("slawekradz")
                .password("password")
                .build();

        ResponseEntity<User> userCreated = executePost("/users", user, User.class);
        long createdUserId = userCreated.getBody().getId();

        User updatedUser = User.builder()
                .firstName("Slawek")
                .lastName("Radzyminski")
                .username("slawekradz")
                .password("password2")
                .build();

        executePut("/users/" + createdUserId, updatedUser);

        ResponseEntity<User> userGotById = getUserById(createdUserId);
        assertThat(userGotById.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userGotById.getBody().getPassword()).isEqualTo("password2");
    }

    @Test
    public void shouldGetAllUsers() {
        ResponseEntity<User[]> users = getAllUsers();
        assertThat(users.getBody().length).isEqualTo(0);
        assertThat(users.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private ResponseEntity<User[]> getAllUsers() {
        return executeGet("/users", User[].class);
    }

    private ResponseEntity<User> getUserById(long id) {
        return executeGet("/users/" + id, User.class);
    }

    private void deleteAllUsers() {
        Arrays.stream(getAllUsers().getBody())
                .map(User::getId)
                .forEach(id -> executeDelete("/users/" + id));
    }

}
