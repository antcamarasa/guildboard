package com.example.demo.exception;
import com.example.demo.exception.Adventurer.AdventurerNotFoundException;
import com.example.demo.exception.Adventurer.DuplicateNameException;
import com.example.demo.exception.Quest.QuestDuplicateTitleException;
import com.example.demo.exception.Quest.QuestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // _______________________________ Adventurer Exception __________________________________
    @ExceptionHandler(DuplicateNameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handlerDuplicateException(DuplicateNameException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AdventurerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlerAdventurerNotFound(AdventurerNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    // _________________________________ Quest Exception ____________________________________
    @ExceptionHandler(QuestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlerQuestNotFound(QuestNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuestDuplicateTitleException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handlerDuplicateQuestTitle(QuestDuplicateTitleException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }
}
