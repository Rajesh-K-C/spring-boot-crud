package com.rajeshkc.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rajeshkc.dto.AddUpdateSoftwareEngineerDto;
import com.rajeshkc.dto.SoftwareEngineerDto;
import com.rajeshkc.entities.SoftwareEngineer;
import com.rajeshkc.repositories.SoftwareEngineerRepository;
import com.rajeshkc.services.SoftwareEngineerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoftwareEngineerServiceImpl implements SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SoftwareEngineerDto> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, SoftwareEngineerDto.class))
                .toList();
    }

    @Override
    public SoftwareEngineerDto saveSoftwareEngineer(AddUpdateSoftwareEngineerDto softwareEngineer) {
        SoftwareEngineer engineer = modelMapper.map(softwareEngineer, SoftwareEngineer.class);
        engineer = softwareEngineerRepository.save(engineer);
        return modelMapper.map(engineer, SoftwareEngineerDto.class);
    }

    @Override
    public SoftwareEngineerDto getSoftwareEngineerById(Long id) {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Software Engineer not found with id " + id));
        return modelMapper.map(softwareEngineer, SoftwareEngineerDto.class);
    }

    @Override
    public SoftwareEngineerDto updateEngineer(Long id, AddUpdateSoftwareEngineerDto details) {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Software Engineer not found with id " + id));

        modelMapper.map(details, softwareEngineer);
        softwareEngineer = softwareEngineerRepository.save(softwareEngineer);
        return modelMapper.map(softwareEngineer, SoftwareEngineerDto.class);
    }

    @Override
    public void deleteEngineer(Long id) {
        if (!softwareEngineerRepository.existsById(id)) {
            new IllegalArgumentException("Software Engineer not found with id " + id);
        }
        softwareEngineerRepository.deleteById(id);
    }
}
