package com.rajeshkc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateSoftwareEngineerDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name should be of length 3 to 30 characters")
    private String name;
    
    @NotBlank(message = "Tech stack is required")
    @Size(min = 3, max = 30, message = "Name should be of length 3 to 30 characters")
    private String techStack;
}
