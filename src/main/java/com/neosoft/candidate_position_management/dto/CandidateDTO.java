package com.neosoft.candidate_position_management.dto;


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
@Builder
public class CandidateDTO {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull
    @Past
    private LocalDate dob;

    @NotEmpty
    private List<Long> positionIds;
}
