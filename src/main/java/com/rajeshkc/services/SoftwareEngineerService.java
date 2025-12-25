package com.rajeshkc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshkc.entities.SoftwareEngineer;
import com.rajeshkc.repositories.SoftwareEngineerRepository;

import jakarta.transaction.Transactional;

@Service
public class SoftwareEngineerService {

    @Autowired
    private SoftwareEngineerRepository softwareEngineerRepository;

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public SoftwareEngineer saveSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        return softwareEngineerRepository.save(softwareEngineer);
    }

    public Optional<SoftwareEngineer> getSoftwareEngineerById(Long id) {
        return softwareEngineerRepository.findById(id);
    }

    @Transactional
    public SoftwareEngineer updateEngineer(Long id, SoftwareEngineer details) {
        return softwareEngineerRepository.findById(id).map(existingEngineer -> {
            existingEngineer.setName(details.getName());
            existingEngineer.setTechStack(details.getTechStack());
            return softwareEngineerRepository.save(existingEngineer);
        }).orElseThrow(() -> new RuntimeException("Engineer not found with id: " + id));
    }

    public boolean deleteEngineer(Long id) {
        if (softwareEngineerRepository.existsById(id)) {
            softwareEngineerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
