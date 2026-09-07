package com.example.demo.exception;
import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, HttpStatus status) {}
