package com.yure.complaints.application.handlers;

import com.yure.complaints.application.exceptions.EntityNotFoundException;
import com.yure.complaints.application.response.EntityNotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllersExceptionHandlers {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.unprocessableEntity().body(new EntityNotFoundResponse(ex.getMessage(), ex.getEntityName()));
    }
}
