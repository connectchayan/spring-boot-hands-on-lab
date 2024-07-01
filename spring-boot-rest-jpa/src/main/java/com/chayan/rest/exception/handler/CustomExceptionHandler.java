package com.chayan.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.chayan.rest.exception.ResourceNotFoundException;
import com.chayan.rest.exception.response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleItemNotFoundException(ResourceNotFoundException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }



}
