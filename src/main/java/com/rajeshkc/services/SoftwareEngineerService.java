package com.rajeshkc.services;

import java.util.List;
import java.util.Optional;

import com.rajeshkc.entities.SoftwareEngineer;

public interface SoftwareEngineerService {
    List<SoftwareEngineer> getAllSoftwareEngineers();
    SoftwareEngineer saveSoftwareEngineer(SoftwareEngineer softwareEngineer);
    Optional<SoftwareEngineer> getSoftwareEngineerById(Long id);
    SoftwareEngineer updateEngineer(Long id, SoftwareEngineer details);
    boolean deleteEngineer(Long id);
}
