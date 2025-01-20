package com.adastra.web.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorManager {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity manage404Error() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manage400Error(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(InputErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record InputErrorValidation(String input, String error) {
        
        public InputErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    
}
