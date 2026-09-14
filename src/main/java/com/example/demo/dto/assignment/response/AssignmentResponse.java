package com.example.demo.dto.assignment.response;

import com.example.demo.model.Adventurer;
import com.example.demo.model.Assignment;
import com.example.demo.model.Quest;

import java.time.OffsetDateTime;

public record AssignmentResponse(Adventurer adventurer, Quest quest, OffsetDateTime assigned_at, OffsetDateTime completed_at){
    public static AssignmentResponse from(Assignment assignment){
        return new AssignmentResponse(assignment.getAdventurer(), assignment.getQuest(), assignment.getAssignedAt(), assignment.getCompletedAt());
    }
}
