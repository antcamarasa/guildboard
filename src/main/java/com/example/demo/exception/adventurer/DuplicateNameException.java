package com.example.demo.exception.adventurer;

public class DuplicateNameException extends RuntimeException{
    public DuplicateNameException(String message){
        super(message);
    }
}
