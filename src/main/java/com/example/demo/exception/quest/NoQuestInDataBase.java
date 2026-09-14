package com.example.demo.exception.quest;

public class NoQuestInDataBase extends RuntimeException{
    public NoQuestInDataBase(String message){
        super(message);
    }
}
