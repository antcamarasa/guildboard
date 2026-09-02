package com.example.demo.model;

import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;

public class Quest {
    private String title;
    private String description;
    private Difficulty difficulty;
    private int requiredLevel;
    private int xpReward;
    private int goldReward;
    private Status status;

    public Quest(String title, String description, Difficulty difficulty, int requiredLevel, int xpReward, int goldReward, Status status){
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.requiredLevel = requiredLevel;
        this.xpReward = xpReward;
        this.goldReward = goldReward;
        this.status = status;
    }


    // Getter & Setter
    public String getTitle(){return this.title;}
}
