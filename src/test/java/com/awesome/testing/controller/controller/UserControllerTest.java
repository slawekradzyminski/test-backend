package com.awesome.testing.controller.controller;

import com.awesome.testing.controller.DomainHelper;
import com.awesome.testing.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.awesome.testing.controller.UserProvider.getRandomUser;
import static com.awesome.testing.controller.UserProvider.getRandomUserWithUsername;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ConstantConditions")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends DomainHelper {

    @Test
    public void shouldUpdateUser() {
        // given
        User user = getRandomUser();
        ResponseEntity<User> userCreated = registerUser(user, User.class);
        long createdUserId = userCreated.getBody().getId();

        User updatedUser = getRandomUserWithUsername("username");

        // when
        executePut("/users/" + createdUserId, updatedUser);

        // then
        ResponseEntity<User> userGotById = getUserById(createdUserId);
        assertThat(userGotById.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userGotById.getBody().getUsername()).isEqualTo("username");
    }

    @Test
    public void shouldGetAllUsers() {
        // when
        ResponseEntity<User[]> users = getAllUsers();

        // then
        assertThat(users.getBody().length).isEqualTo(0);
        assertThat(users.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
