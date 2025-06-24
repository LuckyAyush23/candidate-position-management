package com.neosoft.CandidatePositionManagementSystem.candidate_position_management.controller;

import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.PositionDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.dto.PositionResponseDTO;
import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionResponseDTO> createPosition(@Valid @RequestBody PositionDTO dto) {
        return new ResponseEntity<>(positionService.createPosition(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PositionResponseDTO>> getAllPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponseDTO> getPositionById(@PathVariable Long id) {
        return ResponseEntity.ok(positionService.getPositionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionResponseDTO> updatePosition(
            @PathVariable Long id,
            @Valid @RequestBody PositionDTO dto) {
        return ResponseEntity.ok(positionService.updatePosition(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PositionResponseDTO> patchPosition(
            @PathVariable Long id,
            @RequestBody PositionDTO dto) {
        return ResponseEntity.ok(positionService.patchPosition(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.ok("Position deleted successfully");
    }
}
