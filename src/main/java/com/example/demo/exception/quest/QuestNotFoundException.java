package com.example.demo.exception.quest;

public class QuestNotFoundException extends RuntimeException{
    public QuestNotFoundException(String message){
        super(message);
    }
}
