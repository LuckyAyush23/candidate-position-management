package com.neosoft.CandidatePositionManagementSystem.candidate_position_management.service.impl;

import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.PositionDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.PositionResponseDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.entity.Position;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.exception.CustomException;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.repository.CandidateRepository;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.repository.PositionRepository;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepo;
    private final CandidateRepository candidateRepo;
    private final ModelMapper modelMapper;

    @Override
    public PositionResponseDTO createPosition(PositionDTO dto) {
        if (positionRepo.existsByPositionName(dto.getPositionName())) {
            throw new CustomException("Position already exists");
        }

        Position position = modelMapper.map(dto, Position.class);
        return mapToDto(positionRepo.save(position));
    }

    @Override
    public List<PositionResponseDTO> getAllPositions() {
        return positionRepo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PositionResponseDTO getPositionById(Long id) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new CustomException("Position not found with id: " + id));
        return mapToDto(position);
    }

    @Override
    public PositionResponseDTO updatePosition(Long id, PositionDTO dto) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new CustomException("Position not found"));

        if (!position.getPositionName().equals(dto.getPositionName())
                && positionRepo.existsByPositionName(dto.getPositionName())) {
            throw new CustomException("Another position with this name already exists");
        }

        position.setPositionName(dto.getPositionName());
        return mapToDto(positionRepo.save(position));
    }

    @Override
    public PositionResponseDTO patchPosition(Long id, PositionDTO dto) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new CustomException("Position not found"));

        if (dto.getPositionName() != null &&
                !dto.getPositionName().equals(position.getPositionName()) &&
                positionRepo.existsByPositionName(dto.getPositionName())) {
            throw new CustomException("Another position with this name already exists");
        }

        if (dto.getPositionName() != null) {
            position.setPositionName(dto.getPositionName());
        }

        return mapToDto(positionRepo.save(position));
    }

    @Override
    public void deletePosition(Long id) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new CustomException("Position not found"));

        boolean isMapped = candidateRepo.existsById(position.getId());
        if (isMapped) {
            throw new CustomException("Cannot delete position, it is assigned to candidate(s)");
        }

        positionRepo.delete(position);
    }

    private PositionResponseDTO mapToDto(Position position) {
        return modelMapper.map(position, PositionResponseDTO.class);
    }
}
