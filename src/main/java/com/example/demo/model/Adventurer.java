package com.example.demo.model;
import com.example.demo.model.enums.AdventurerType;

public class Adventurer {
    private String name;
    private AdventurerType type;
    private int level;
    private int xp;
    private int gold;


    public Adventurer(String name, AdventurerType type){
        this.name = name;
        this.type = type;
        this.level = 1;
        this.xp = 0;
        this.gold = 0;
    }


    // Getter
    public String getName(){
        return this.name;
    }

    public AdventurerType getCharacterType(){
        return this.type;
    }
}
