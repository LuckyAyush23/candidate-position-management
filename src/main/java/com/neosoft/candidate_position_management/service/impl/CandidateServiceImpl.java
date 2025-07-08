package com.neosoft.candidate_position_management.service.impl;

import com.neosoft.candidate_position_management.dto.CandidateDTO;
import com.neosoft.candidate_position_management.dto.CandidatePatchDTO;
import com.neosoft.candidate_position_management.dto.CandidateResponseDTO;
import com.neosoft.candidate_position_management.entity.Candidate;
import com.neosoft.candidate_position_management.entity.Position;
import com.neosoft.candidate_position_management.exception.CustomException;
import com.neosoft.candidate_position_management.exception.ResourceNotFoundException;
import com.neosoft.candidate_position_management.repository.CandidateRepository;
import com.neosoft.candidate_position_management.repository.PositionRepository;
import com.neosoft.candidate_position_management.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final PositionRepository positionRepo;
    private final ModelMapper modelMapper;

    @Override
    public CandidateResponseDTO createCandidate(CandidateDTO dto) {
        if (candidateRepository.existsByEmail(dto.getEmail())) {
            throw new CustomException("Email already exists");
        }

        Candidate candidate = modelMapper.map(dto, Candidate.class);
        candidate.setPositions(positionRepo.findAllById(dto.getPositionIds()));

        Candidate saved = candidateRepository.save(candidate);
        return mapToDto(saved);
    }

    @Override
    public CandidateResponseDTO updateCandidate(Long id, CandidateDTO dto) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + id));

        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setDob(dto.getDob());
        candidate.setPositions(positionRepo.findAllById(dto.getPositionIds()));

        Candidate updated = candidateRepository.save(candidate);
        return modelMapper.map(updated, CandidateResponseDTO.class);
    }

    @Override
    public CandidateResponseDTO patchCandidate(Long id, CandidatePatchDTO dto) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + id));


        if (dto.getName() != null) candidate.setName(dto.getName());

        if (dto.getEmail() != null && !dto.getEmail().equals(candidate.getEmail())) {
            if (candidateRepository.existsByEmail(dto.getEmail())) {
                throw new CustomException("Email already exists");
            }
            candidate.setEmail(dto.getEmail());
        }

        if (dto.getDob() != null) {
            candidate.setDob(dto.getDob());
        }

        if (dto.getPositionIds() != null) {
            candidate.setPositions(positionRepo.findAllById(dto.getPositionIds()));
        }

        return mapToDto(candidateRepository.save(candidate));
    }

    @Override
    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponseDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CustomException("Candidate not found with id: " + id));
        return mapToDto(candidate);
    }

    @Override
    public void deleteCandidateById(Long id) {
        if (!candidateRepository.existsById(id)) {
            throw new CustomException("Id is invalid");
        }
        candidateRepository.deleteById(id);
    }

    private CandidateResponseDTO mapToDto(Candidate candidate) {
        CandidateResponseDTO dto = modelMapper.map(candidate, CandidateResponseDTO.class);
        List<String> names = candidate.getPositions().stream()
                .map(Position::getPositionName)
                .collect(Collectors.toList());
        dto.setPositionNames(names);
        return dto;
    }
}

