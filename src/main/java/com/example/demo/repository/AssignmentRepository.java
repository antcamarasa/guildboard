package com.example.demo.repository;

import com.example.demo.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findByAdventurerId(Integer id);
    Optional<Assignment>findByAdventurerIdAndQuestId(Integer adventurerId, Integer questId);

}
