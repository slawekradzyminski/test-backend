package com.awesome.testing.service;

import com.awesome.testing.dto.Pet;
import com.awesome.testing.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        List<Pet> informations = new ArrayList<>();
        petRepository.findAll().forEach(informations::add);
        return informations;
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
