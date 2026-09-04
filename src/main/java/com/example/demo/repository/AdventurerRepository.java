package com.example.demo.repository;

import com.example.demo.model.Adventurer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventurerRepository extends JpaRepository<Adventurer, Long>{}
