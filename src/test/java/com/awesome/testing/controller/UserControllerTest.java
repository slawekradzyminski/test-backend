package com.awesome.testing.controller;

import com.awesome.testing.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends HttpHelper {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldGetUsers() {
        ResponseEntity<User[]> users = executeGet("/users", User[].class);
        assertThat(users.getBody().length).isEqualTo(2);
        assertThat(users.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
