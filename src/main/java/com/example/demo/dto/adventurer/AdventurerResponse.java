package com.example.demo.dto.adventurer;
import com.example.demo.model.enums.AdventurerType;

public record AdventurerResponse(Integer id, String name, AdventurerType adventurerType, int gold, int xp, int level){}
