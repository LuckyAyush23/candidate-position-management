package com.neosoft.candidate_position_management.dto;

import com.neosoft.candidate_position_management.validation.MinAge;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePatchDTO {

    private String name;

    @Email(message = "Email must be valid")
    private String email;

    @MinAge(value = 18, message = "Candidate must be at least 18 years old")
    private LocalDate dob;

    private List<Long> positionIds;
}
