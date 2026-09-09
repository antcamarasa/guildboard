package com.example.demo.constant;

public enum Constant {
    // Enum value
    ADVENTURER_NOT_FIND_IN_DB("Adventurer not find in database"),
    NAME_ALREADY_EXIST_IN_DB("Name already exist in database"),
    QUEST_NOT_FIND_IN_DB("Quest not find in database"),
    QUEST_DUPLICATE_TITLE("Quest duplicate title");

    // Fields
    private final String message;

    // Constructor
    Constant(String message){
        this.message = message;
    }

    // Getter
    public String getMessage(){
        return this.message;
    }
}
