package com.example.demo.Character;

public enum CharacterType {
    WARRIOR("Warrior"),
    MAGE("Mage"),
    RANGER("Ranger"),
    CLERIC("Cleric");

    private final String type;
    CharacterType(String type){
        this.type =type;
    }

    // Getter
    public String getType(){
        return this.type;
    }
}
