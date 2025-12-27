package com.rajeshkc.services;

import java.util.List;
import java.util.Map;

import com.rajeshkc.dto.AddUpdateSoftwareEngineerDto;
import com.rajeshkc.dto.SoftwareEngineerDto;

public interface SoftwareEngineerService {
    List<SoftwareEngineerDto> getAllSoftwareEngineers();
    SoftwareEngineerDto saveSoftwareEngineer(AddUpdateSoftwareEngineerDto softwareEngineer);
    SoftwareEngineerDto getSoftwareEngineerById(Long id);
    SoftwareEngineerDto updateEngineer(Long id, AddUpdateSoftwareEngineerDto details);
    void deleteEngineer(Long id);
    SoftwareEngineerDto updatePartialSoftwareEngineer(Long id, Map<Object, Object> updates);
}
