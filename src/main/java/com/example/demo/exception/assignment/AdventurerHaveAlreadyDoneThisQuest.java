package com.example.demo.exception.assignment;

public class AdventurerHaveAlreadyDoneThisQuest extends RuntimeException {
    public AdventurerHaveAlreadyDoneThisQuest(String message){
        super(message);
    }
}
