package com.example.demo.Character;

public class Adventurer {
    private String name;
    private CharacterType type;
    private int level;
    private int xp;
    private int gold;


    public Adventurer(String name, CharacterType type){
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

    public CharacterType getCharacterType(){
        return this.type;
    }
}
