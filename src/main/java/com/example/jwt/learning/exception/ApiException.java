package com.example.jwt.learning.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
