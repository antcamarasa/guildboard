package com.example.demo.model.enums;

public enum Difficulty {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    EPIC("Epic");

    private String value;
    Difficulty(String value){
        this.value = value;
    }

    // Getters
    public String getValue(){
        return this.value;
    }
}
