package com.awesome.testing.service;

import com.awesome.testing.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awesome.testing.dto.Information;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service
public class InformationService {

    private final InformationRepository informationRepository;

    @Autowired
    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    public List<Information> getAllInformation() {
        return stream(informationRepository.findAll().spliterator(), false)
                .collect(toList());
    }

    public Optional<Information> getInformationById(long id) {
        return informationRepository.findById(id);
    }

    public Optional<Information> getInformationByName(String name) {
        return informationRepository.findByName(name);
    }

    public void deleteById(long id) {
        informationRepository.deleteById(id);
    }

    public void delete(Information information) {
        informationRepository.delete(information);
    }

    public Information save(Information information) {
        return informationRepository.save(information);
    }

    public void resetIdAutoincrement() {
        informationRepository.resetAutoincrement();
    }

}
