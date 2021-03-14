package com.awesome.testing.controller;

import com.awesome.testing.dto.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProvider {

    public static User getRandomUserWithUsernameAndPassword(String username, String password) {
        return User.builder()
                .firstName(RandomString.make(10))
                .lastName(RandomString.make(10))
                .username(username)
                .password(password)
                .build();
    }

    public static User getRandomUserWithUsername(String username) {
        return User.builder()
                .firstName(RandomString.make(10))
                .lastName(RandomString.make(10))
                .username(username)
                .password(RandomString.make(10))
                .build();
    }

    public static User getRandomUser() {
        return User.builder()
                .firstName(RandomString.make(10))
                .lastName(RandomString.make(10))
                .username(RandomString.make(10))
                .password(RandomString.make(10))
                .build();
    }

}
