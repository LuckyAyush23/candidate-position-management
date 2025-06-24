package com.neosoft.CandidatePositionManagementSystem.candidate_position_management.service;

import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.PositionDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.PositionResponseDTO;

import java.util.List;

public interface PositionService {

    PositionResponseDTO createPosition(PositionDTO dto);

    List<PositionResponseDTO> getAllPositions();

    PositionResponseDTO getPositionById(Long id);

    PositionResponseDTO updatePosition(Long id, PositionDTO dto);

    PositionResponseDTO patchPosition(Long id, PositionDTO dto);

    void deletePosition(Long id);
}
