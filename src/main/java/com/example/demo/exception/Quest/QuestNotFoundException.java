package com.example.demo.exception.Quest;

public class QuestNotFoundException extends RuntimeException{
    public QuestNotFoundException(String message){
        super(message);
    }
}
