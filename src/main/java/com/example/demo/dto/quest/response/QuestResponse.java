package com.example.demo.dto.quest.response;

import com.example.demo.model.Quest;
import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;

public record QuestResponse(
        Integer id,
        String title,
        String description,
        Difficulty difficulty,
        int requiredLevel,
        int goldReward,
        int xpReward,
        Status status) {

    // Question mon DTO connait la quête ? est-ce grave ?
    public static QuestResponse from(Quest quest){
        return new QuestResponse(
                quest.getId(),
                quest.getTitle(),
                quest.getDescription(),
                quest.getDifficulty(),
                quest.getRequiredLevel(),
                quest.getGoldReward(),
                quest.getXpReward(),
                quest.getStatus());
    }
}
