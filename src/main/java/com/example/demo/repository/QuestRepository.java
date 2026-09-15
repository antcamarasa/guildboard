package com.example.demo.repository;

import com.example.demo.model.Quest;
import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestRepository  extends JpaRepository<Quest, Integer>, JpaSpecificationExecutor<Quest> {
    boolean existsByTitle(String title);

    // Deprecated
    @Query("SELECT q from Quest q WHERE q.status = :status")
    List<Quest> filterByStatus(@Param("status") Status status);

    @Query("SELECT q from Quest q WHERE q.difficulty = :difficulty")
    List<Quest> filterByDifficulty(@Param("difficulty")Difficulty difficulty);

    @Query("SELECT q from Quest q where q.status = :status AND q.difficulty = :difficulty")
    List<Quest> filterByStatusAndDifficulty(@Param("status") Status status, @Param("difficulty")Difficulty difficulty);
}
