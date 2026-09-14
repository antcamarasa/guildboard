package com.example.demo.constant;

public enum Constant {
    // Enum value
    ADVENTURER_NOT_FIND_IN_DB("Adventurer not find in database"),
    NAME_ALREADY_EXIST_IN_DB("Name already exist in database"),

    QUEST_NOT_FIND_IN_DB("Quest not find in database"),
    QUEST_DUPLICATE_TITLE("Quest duplicate title"),

    ADVENTURER_LEVEL_INSUFFISANT("RG1 rules, adventure level is inférior to quest required level."),
    ADVENTURER_HAVE_ASSIGNMENT_IN_PROGRESS("Adventurer already have assignment in progress"),
    QUEST_NOT_AVAILABLE("Quest is not available"),

    ASSIGNMENT_NOT_FIND("Assignment not find in database."),
    ADVENTURER_ALREADY_DONE_THIS_QUEST("Adventurer have already done this quest"),

    NO_QUEST_IN_DATABASE("No quest in database");
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
