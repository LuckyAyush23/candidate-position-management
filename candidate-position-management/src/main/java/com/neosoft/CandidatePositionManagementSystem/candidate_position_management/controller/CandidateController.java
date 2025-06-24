package com.neosoft.CandidatePositionManagementSystem.candidate_position_management.controller;

import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.CandidateDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.CandidatePatchDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.CandidateResponseDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.entity.Candidate;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateResponseDTO> createCandidate(@Valid @RequestBody CandidateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.createCandidate(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> patchCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidatePatchDTO dto) {
        return ResponseEntity.ok(candidateService.patchCandidate(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable Long id) {
        return ResponseEntity.ok(candidateService.getCandidateById(id));
    }
}
