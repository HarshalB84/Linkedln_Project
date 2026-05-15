package com.project.linkedlnProject.userService.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiError apiError = new ApiError(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(BadRequestException e) {
        ApiError apiError = new ApiError(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(RuntimeException e) {
        ApiError apiError = new ApiError(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
