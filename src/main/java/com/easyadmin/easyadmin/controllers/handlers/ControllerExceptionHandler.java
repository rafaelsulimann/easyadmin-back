package com.easyadmin.easyadmin.controllers.handlers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.easyadmin.easyadmin.dtos.CustomErrorDTO;
import com.easyadmin.easyadmin.dtos.FieldMessageDTO;
import com.easyadmin.easyadmin.dtos.ValidationErrorDTO;
import com.easyadmin.easyadmin.services.exceptions.DatabaseException;
import com.easyadmin.easyadmin.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO error = new ValidationErrorDTO(LocalDateTime.now(), status.value(), "Dados inválidos", request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addError(new FieldMessageDTO(f.getField(), f.getDefaultMessage()));
        }
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO error = new CustomErrorDTO(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}
