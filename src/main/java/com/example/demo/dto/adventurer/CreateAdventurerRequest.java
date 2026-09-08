package com.example.demo.dto.adventurer;

import com.example.demo.model.enums.AdventurerType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateAdventurerRequest {
    private final @NotNull @Size(min = 2, max = 50) String name;
    private final @NotNull AdventurerType characterType;

    public CreateAdventurerRequest(String name, AdventurerType characterType){
        this.name = name;
        this.characterType = characterType;
    }

    // Getter
    public String getName(){
        return this.name;
    }
    public AdventurerType getCharacterType(){
        return this.characterType;
    }
}
