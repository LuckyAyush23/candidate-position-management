package com.neosoft.candidate_position_management.controller;

import com.neosoft.candidate_position_management.dto.PositionDTO;
import com.neosoft.candidate_position_management.dto.PositionResponseDTO;
import com.neosoft.candidate_position_management.dto.common.ApiResponse;
import com.neosoft.candidate_position_management.service.PositionService;
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
    public ResponseEntity<ApiResponse<PositionResponseDTO>> createPosition(@Valid @RequestBody PositionDTO dto) {
            PositionResponseDTO responseDTO = positionService.createPosition(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Postion created successfully" , responseDTO));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PositionResponseDTO>>> getAllPositions() {
        List<PositionResponseDTO> positions = positionService.getAllPositions();
        return ResponseEntity.ok(ApiResponse.success("Positions fetched successfully", positions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PositionResponseDTO>> getPositionById(@PathVariable Long id) {
        PositionResponseDTO position = positionService.getPositionById(id);
        return ResponseEntity.ok(ApiResponse.success("Position fetched successfully", position));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PositionResponseDTO>> updatePosition(
            @PathVariable Long id,
            @Valid @RequestBody PositionDTO dto) {
        PositionResponseDTO updated = positionService.updatePosition(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Position updated successfully", updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PositionResponseDTO>> patchPosition(
            @PathVariable Long id,
            @RequestBody PositionDTO dto) {
        PositionResponseDTO patched = positionService.patchPosition(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Position patched successfully", patched));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.ok(ApiResponse.success("Position deleted successfully", null));
    }
}
