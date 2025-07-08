package com.neosoft.candidate_position_management.service;

import com.neosoft.candidate_position_management.dto.CandidateDTO;
import com.neosoft.candidate_position_management.dto.CandidatePatchDTO;
import com.neosoft.candidate_position_management.dto.CandidateResponseDTO;

import java.util.List;

public interface CandidateService {
    CandidateResponseDTO createCandidate(CandidateDTO dto);
    CandidateResponseDTO updateCandidate(Long id, CandidateDTO dto);
    CandidateResponseDTO patchCandidate(Long id, CandidatePatchDTO dto);
    List<CandidateResponseDTO> getAllCandidates();
    CandidateResponseDTO getCandidateById(Long id);
    void deleteCandidateById(Long id);

}