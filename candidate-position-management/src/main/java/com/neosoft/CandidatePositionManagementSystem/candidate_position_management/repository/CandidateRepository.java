package com.neosoft.CandidatePositionManagementSystem.candidate_position_management.repository;

import com.neosoft.CandidatePositionManagementSystem.candidate_position_management.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByEmail(String email);
}

