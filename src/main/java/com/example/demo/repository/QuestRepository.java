package com.example.demo.repository;

import com.example.demo.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

// Ici l'interface JpaRepository porte toutes les méthodes du CRUD.
public interface QuestRepository  extends JpaRepository<Quest, Long>{}
