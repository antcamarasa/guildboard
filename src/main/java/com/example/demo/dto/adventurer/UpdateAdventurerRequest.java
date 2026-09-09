package com.example.demo.dto.adventurer;
import com.example.demo.model.enums.AdventurerType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAdventurerRequest(
        @NotNull @NotBlank @Size(min = 2, max = 50) String name,
        @NotNull AdventurerType adventurerType,
        @NotNull @Min(0) Integer level,
        @NotNull @Min(0) Integer xp,
        @NotNull @Min(0) Integer gold) {

}
