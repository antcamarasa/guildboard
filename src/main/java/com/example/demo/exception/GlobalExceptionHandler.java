package com.example.demo.exception;
import com.example.demo.exception.adventurer.AdventurerNotFoundException;
import com.example.demo.exception.adventurer.DuplicateNameException;
import com.example.demo.exception.assignment.*;
import com.example.demo.exception.quest.QuestDuplicateTitleException;
import com.example.demo.exception.quest.QuestNotFoundException;
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

    @ExceptionHandler(AdventurerLevelInsuffisant.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handlerAdventurerLevelIsInsuffisant(AdventurerLevelInsuffisant e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(QuestNotAvailable.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handlerQuestNotAvailable(QuestNotAvailable e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AdventurerHaveAssignmentInProgress.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handlerAdventurerHaveAssignmentInProgress(AdventurerHaveAssignmentInProgress e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AssignmentNotFind.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlerAssignmentNotfound(AssignmentNotFind e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AdventurerHaveAlreadyDoneThisQuest.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handlerAdventurerHaveAlreadyDoneThisQuest(AdventurerHaveAlreadyDoneThisQuest e){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

}
