package com.example.demo.model.enums;

public enum AdventurerType {
    WARRIOR("Warrior"),
    MAGE("Mage"),
    RANGER("Ranger"),
    CLERIC("Cleric");

    private final String type;
    AdventurerType(String type){
        this.type =type;
    }

    // Getter
    public String getType(){
        return this.type;
    }
}
