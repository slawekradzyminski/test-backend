package com.awesome.testing.controller.controller;

import com.awesome.testing.controller.DomainHelper;
import com.awesome.testing.dto.ErrorDto;
import com.awesome.testing.dto.LoginDto;
import com.awesome.testing.dto.LoginResponseDto;
import com.awesome.testing.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.awesome.testing.controller.UserProvider.getRandomUserWithUsernameAndPassword;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ConstantConditions")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticateControllerTest extends DomainHelper {

    @Test
    public void shouldLoginUser() {
        // given
        User user = getRandomUserWithUsernameAndPassword("username", "password");
        registerUser(user);

        LoginDto loginDto = LoginDto.builder().username("username").password("password").build();

        // when
        ResponseEntity<LoginResponseDto> response = loginUser(loginDto, LoginResponseDto.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getUsername()).isEqualTo("username");
    }

    @Test
    public void shouldFailToLoginWithWrongUsername() {
        // given
        User user = getRandomUserWithUsernameAndPassword("username", "password");
        registerUser(user);

        LoginDto loginDto = LoginDto.builder().username("wrongUsername").password("password").build();

        // when
        ResponseEntity<ErrorDto> response = loginUser(loginDto, ErrorDto.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().getMessage()).isEqualTo("Login failed - bad username or password");
    }

    @Test
    public void shouldFailToLoginWithWrongPassword() {
        // given
        User user = getRandomUserWithUsernameAndPassword("username", "password");
        registerUser(user);

        LoginDto loginDto = LoginDto.builder().username("username").password("wrongPassword").build();

        // when
        ResponseEntity<ErrorDto> response = loginUser(loginDto, ErrorDto.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().getMessage()).isEqualTo("Login failed - bad username or password");
    }
}
