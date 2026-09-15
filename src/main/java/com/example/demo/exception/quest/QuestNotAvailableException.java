package com.example.demo.exception.quest;

public class QuestNotAvailableException extends RuntimeException{
    String message;

    public String getMessage() {
        return message;
    }
}
