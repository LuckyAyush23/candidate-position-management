package com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionResponseDTO {
    private Long id;
    private String positionName;
}