package com.neosoft.candidate_position_management.dto;


import com.neosoft.candidate_position_management.validation.MinAge;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull
    @Past
    @MinAge(value = 18, message = "Candidate must be at least 18 years old")
    private LocalDate dob;

    @NotEmpty(message = "Position list cannot be empty")
    private List<Long> positionIds;
}
