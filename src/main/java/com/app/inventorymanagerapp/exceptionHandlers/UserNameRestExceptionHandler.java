package com.app.inventorymanagerapp.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNameRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserNameErrorResponse> handleException(UserNameAlreadyExists exc){
        UserNameErrorResponse error = new UserNameErrorResponse();
        error.setStatus(HttpStatus.FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<UserNameErrorResponse> handleException(Exception e){
        UserNameErrorResponse error = new UserNameErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
