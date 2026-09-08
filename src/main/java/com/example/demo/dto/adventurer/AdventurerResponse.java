package com.example.demo.dto.adventurer;
import com.example.demo.model.Adventurer;
import com.example.demo.model.enums.AdventurerType;


public record AdventurerResponse(
        Integer id,
        String name,
        AdventurerType adventurerType,
        int gold,
        int xp,
        int level)
{
    public static AdventurerResponse from(Adventurer adventurer){
        return new AdventurerResponse(
                adventurer.getId(),
                adventurer.getName(),
                adventurer.getAdventurerType(),
                adventurer.getGold(),
                adventurer.getXp(),
                adventurer.getLevel()
        );
    }
}
