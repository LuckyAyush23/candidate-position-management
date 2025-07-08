package com.neosoft.candidate_position_management.controller;

import com.neosoft.candidate_position_management.dto.CandidateDTO;
import com.neosoft.candidate_position_management.dto.CandidatePatchDTO;
import com.neosoft.candidate_position_management.dto.CandidateResponseDTO;
import com.neosoft.candidate_position_management.dto.common.ApiResponse;
import com.neosoft.candidate_position_management.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<ApiResponse<CandidateResponseDTO>> createCandidate(@Valid @RequestBody CandidateDTO dto){
        CandidateResponseDTO response = candidateService.createCandidate(dto);
        return ResponseEntity.status(201).body(ApiResponse.success("Candidate created successfully",response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CandidateResponseDTO>> updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidateDTO dto) {

        CandidateResponseDTO updatedCandidate = candidateService.updateCandidate(id, dto);

        return ResponseEntity.ok(ApiResponse.success("Candidate updated successfully", updatedCandidate));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CandidateResponseDTO>> patchCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidatePatchDTO dto) {
        CandidateResponseDTO response = candidateService.patchCandidate(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Candidate updated successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CandidateResponseDTO>>> getAllCandidates() {
        List<CandidateResponseDTO> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(ApiResponse.success("Candidates fetched successfully", candidates));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CandidateResponseDTO>> getCandidateById(@PathVariable Long id) {
        CandidateResponseDTO candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(ApiResponse.success("Candidate fetched successfully", candidate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCandidateById(@PathVariable Long id) {
        candidateService.deleteCandidateById(id);
        return ResponseEntity.ok(ApiResponse.success("Candidate deleted successfully", null));
    }
}
