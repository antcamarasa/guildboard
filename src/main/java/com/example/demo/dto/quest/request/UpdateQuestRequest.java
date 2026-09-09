package com.example.demo.dto.quest.request;

import com.example.demo.model.enums.Difficulty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateQuestRequest(
        @NotBlank @Size(min = 3, max = 100) String title,
        @NotBlank @Size(min = 10, max = 500) String description,
        @NotNull Difficulty difficulty,
        @NotNull @Min(1) Integer xpReward,
        @NotNull @Min(1) Integer goldReward,
        @NotNull @Min(0) Integer requiredLevel
        )
{}
