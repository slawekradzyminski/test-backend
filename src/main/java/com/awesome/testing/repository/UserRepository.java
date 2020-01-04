package com.awesome.testing.repository;

import com.awesome.testing.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
