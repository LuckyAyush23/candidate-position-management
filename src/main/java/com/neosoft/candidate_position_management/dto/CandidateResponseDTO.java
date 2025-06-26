package com.neosoft.candidate_position_management.dto;

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
public class CandidateResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private List<String> positionNames;
}

