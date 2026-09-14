package com.example.demo.dto.assignment.request;

import com.example.demo.model.Adventurer;
import com.example.demo.model.Quest;

public record CreateAssignmentRequest(Adventurer adventurer, Quest quest){}