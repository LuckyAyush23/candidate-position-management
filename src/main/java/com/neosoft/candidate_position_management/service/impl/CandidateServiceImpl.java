package com.neosoft.candidate_position_management.service.impl;

import com.neosoft.candidate_position_management.dto.CandidateDTO;
import com.neosoft.candidate_position_management.dto.CandidatePatchDTO;
import com.neosoft.candidate_position_management.dto.CandidateResponseDTO;
import com.neosoft.candidate_position_management.entity.Candidate;
import com.neosoft.candidate_position_management.entity.Position;
import com.neosoft.candidate_position_management.exception.CustomException;
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

    private final CandidateRepository candidateRepo;
    private final PositionRepository positionRepo;
    private final ModelMapper modelMapper;

    @Override
    public CandidateResponseDTO createCandidate(CandidateDTO dto) {
        if (candidateRepo.existsByEmail(dto.getEmail())) {
            throw new CustomException("Email already exists");
        }

        Candidate candidate = modelMapper.map(dto, Candidate.class);
        candidate.setPositions(positionRepo.findAllById(dto.getPositionIds()));

        Candidate saved = candidateRepo.save(candidate);
        return mapToDto(saved);
    }

    @Override
    public CandidateResponseDTO patchCandidate(Long id, CandidatePatchDTO dto) {
        Candidate candidate = candidateRepo.findById(id)
                .orElseThrow(() -> new CustomException("Candidate not found"));

        if (dto.getName() != null) candidate.setName(dto.getName());

        if (dto.getEmail() != null && !dto.getEmail().equals(candidate.getEmail())) {
            if (candidateRepo.existsByEmail(dto.getEmail())) {
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

        return mapToDto(candidateRepo.save(candidate));
    }
    @Override
    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateRepo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponseDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepo.findById(id)
                .orElseThrow(() -> new CustomException("Candidate not found with id: " + id));
        return mapToDto(candidate);
    }

    @Override
    public void deleteCandidateById(Long id) {
        if (!candidateRepo.existsById(id)) {
            throw new CustomException("Id is invalid");
        }
        candidateRepo.deleteById(id);
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

