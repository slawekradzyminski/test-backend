package com.awesome.testing.controller;

import com.awesome.testing.dto.Pet;
import com.awesome.testing.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pet")
    public ResponseEntity<?> getAllPets() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(petService.getAllPets());
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<?> getPetById(@PathVariable int id) {
        if (!petService.getById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is not Pet with such id");
        }

        Optional<Pet> pet = petService.getById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pet);
    }

    @PostMapping(path = "/pet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPet(@RequestBody Pet pet) {
        if (petService.getByName(pet.getName()).isPresent()) {
            return ResponseEntity.unprocessableEntity().body("Name already exists. Must be unique");
        }

        Pet saved = petService.save(pet);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(saved);
    }

    @DeleteMapping("/pet/{id}")
    public ResponseEntity<?> deleteInfo(@PathVariable int id) {
        if (!petService.getById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is not Pet with such id");
        }

        petService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
