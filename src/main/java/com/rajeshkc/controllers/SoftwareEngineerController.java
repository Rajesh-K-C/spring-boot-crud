package com.rajeshkc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshkc.entities.ApiResponse;
import com.rajeshkc.entities.SoftwareEngineer;
import com.rajeshkc.services.SoftwareEngineerService;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    @Autowired
    private SoftwareEngineerService softwareEngineerService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SoftwareEngineer>>> getEngineers() {
        List<SoftwareEngineer> engineers = softwareEngineerService.getAllSoftwareEngineers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched all software engineers", engineers));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SoftwareEngineer>> create(@RequestBody SoftwareEngineer softwareEngineer) {
        SoftwareEngineer saved = softwareEngineerService.saveSoftwareEngineer(softwareEngineer);
        return new ResponseEntity<>(
                new ApiResponse<>(true, "Software Engineer created", saved),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SoftwareEngineer>> getEngineerById(@PathVariable Long id) {
        return softwareEngineerService.getSoftwareEngineerById(id)
                .map(e -> ResponseEntity.ok(new ApiResponse<>(true, "Found", e)))
                .orElse(new ResponseEntity<>(new ApiResponse<>(false, "Not found", null),
                        HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<SoftwareEngineer>> updateEngineer(@PathVariable Long id,
            @RequestBody SoftwareEngineer softwareEngineer) {
        try {
            SoftwareEngineer updatedEngineer = softwareEngineerService.updateEngineer(id, softwareEngineer);
            return ResponseEntity.ok(new ApiResponse<>(true, "Engineer updated", updatedEngineer));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEngineer(@PathVariable Long id) {
        if (softwareEngineerService.deleteEngineer(id)) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Software Engineer deleted", null));
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "Software Engineer not found", null),
                HttpStatus.NOT_FOUND);
    }
}
