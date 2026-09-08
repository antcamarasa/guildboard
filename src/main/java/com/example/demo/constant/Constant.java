package com.example.demo.constant;

public enum Constant {
    ADVENTURER_NOT_FIND_IN_DB("Adventurer not find in database"),
    NAME_ALREADY_EXIST_IN_DB("Name already exist in database");

    private final String message;
    Constant(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
