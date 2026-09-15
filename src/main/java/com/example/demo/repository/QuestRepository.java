package com.example.demo.repository;

import com.example.demo.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface QuestRepository  extends JpaRepository<Quest, Integer>, JpaSpecificationExecutor<Quest> {
    boolean existsByTitle(String title);
}
