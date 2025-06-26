package com.neosoft.candidate_position_management.repository;

import com.neosoft.candidate_position_management.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    boolean existsByPositionName(String name);
}
