package com.awesome.testing.service;

import com.awesome.testing.dto.LoginDto;
import com.awesome.testing.dto.User;
import com.awesome.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return stream(userRepository.findAll().spliterator(), false)
                .collect(toList());
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean userAlreadyExists(String username) {
        return getAllUser().stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public Optional<User> authenticate(LoginDto loginDto) {
        return getAllUser().stream()
                .filter(user -> user.getUsername().equals(loginDto.getUsername()))
                .filter(user -> user.getPassword().equals(loginDto.getPassword()))
                .findFirst();
    }

}
