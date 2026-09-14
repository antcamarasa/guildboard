package com.example.demo.exception.assignment;

public class QuestNotAvailable extends RuntimeException{
    public QuestNotAvailable(String message){
        super(message);
    }
}
