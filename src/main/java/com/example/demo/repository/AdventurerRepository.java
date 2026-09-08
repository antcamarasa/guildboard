package com.example.demo.repository;

import com.example.demo.dto.adventurer.UpdateAdventurerRequest;
import com.example.demo.model.Adventurer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventurerRepository extends JpaRepository<Adventurer, Integer>{
    Boolean existsByName(String name);
}
