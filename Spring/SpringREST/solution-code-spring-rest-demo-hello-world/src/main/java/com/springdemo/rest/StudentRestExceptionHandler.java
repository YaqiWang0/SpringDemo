package com.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e){
        StudentErrorResponse studentErrorResponse=new StudentErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(e.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception e){
        StudentErrorResponse studentErrorResponse=new StudentErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(e.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
