package com.awesome.testing.repository;

import com.awesome.testing.dto.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Integer> {

    Optional<Pet> findByName(String name);

}