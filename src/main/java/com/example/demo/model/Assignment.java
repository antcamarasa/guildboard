package com.example.demo.model;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

public class Assignment {
    /**
     * id => ?
     * adventurer_id => ? Adventurer
     * quest_id => ? Quest
     *
     */
    Adventurer adventurer;
    Quest quest;
    LocalDateTime assignedAt;
    LocalDateTime completedAt;

    public Assignment(Adventurer adventurer, Quest quest, LocalDateTime assignedAt, LocalDateTime completedAt){
        this.adventurer = adventurer;
        this.quest = quest;
        this.assignedAt = LocalDateTime.now();
        this.completedAt = completedAt; // Alors la ?? null par défault et on l'update a la fin de countdown ?
    }

    // Getter & Setter
    public LocalDateTime getCompletedAt(){
        return this.completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt){
        this.completedAt = completedAt;
    }
}
