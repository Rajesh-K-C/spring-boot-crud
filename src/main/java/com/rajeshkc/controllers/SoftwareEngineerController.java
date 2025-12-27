package com.rajeshkc.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshkc.dto.AddUpdateSoftwareEngineerDto;
import com.rajeshkc.dto.SoftwareEngineerDto;
import com.rajeshkc.services.SoftwareEngineerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    @GetMapping
    public ResponseEntity<List<SoftwareEngineerDto>> getEngineers() {
        return ResponseEntity.ok(softwareEngineerService.getAllSoftwareEngineers());
    }

    @PostMapping
    public ResponseEntity<SoftwareEngineerDto> create(
            @RequestBody @Valid AddUpdateSoftwareEngineerDto softwareEngineer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(softwareEngineerService.saveSoftwareEngineer(softwareEngineer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoftwareEngineerDto> getEngineerById(@PathVariable Long id) {
        return ResponseEntity.ok(softwareEngineerService.getSoftwareEngineerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoftwareEngineerDto> updateEngineer(@PathVariable Long id,
            @RequestBody @Valid AddUpdateSoftwareEngineerDto softwareEngineer) {
        return ResponseEntity.ok(softwareEngineerService.updateEngineer(id, softwareEngineer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngineer(@PathVariable Long id) {
        softwareEngineerService.deleteEngineer(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SoftwareEngineerDto> updatePartialSoftwareEngineer(@PathVariable Long id,
            @RequestBody Map<Object, Object> updates) {
        return ResponseEntity.ok(softwareEngineerService.updatePartialSoftwareEngineer(id, updates));
    }
}
