package com.awesome.testing.controller.controller;

import com.awesome.testing.controller.DomainHelper;
import com.awesome.testing.dto.ErrorDto;
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
public class RegisterControllerTest extends DomainHelper {

    @Test
    public void shouldCreateUser() {
        // given
        User user = getRandomUser();

        // when
        ResponseEntity<User> userCreated = registerUser(user, User.class);

        // then
        assertThat(userCreated.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        long createdUserId = userCreated.getBody().getId();
        ResponseEntity<User> userGotById = getUserById(createdUserId);
        assertThat(userGotById.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldNotCreateUserWithTheSameUsername() {
        // given
        String username = "username";
        User user = getRandomUserWithUsername(username);
        registerUser(user);

        User duplicatedUser = getRandomUserWithUsername(username);

        // when
        ResponseEntity<ErrorDto> response = registerUser(duplicatedUser, ErrorDto.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("User already exists");
    }
}
