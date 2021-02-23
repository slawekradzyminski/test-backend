package com.awesome.testing.service;

import com.awesome.testing.dto.Pet;
import com.awesome.testing.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return stream(petRepository.findAll().spliterator(), false)
                .collect(toList());
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public void deleteById(int id) {
        petRepository.deleteById(id);
    }

    public Optional<Pet> getById(int id) {
        return petRepository.findById(id);
    }

    public Optional<Pet> getByName(String name) {
        return petRepository.findByName(name);
    }
}
