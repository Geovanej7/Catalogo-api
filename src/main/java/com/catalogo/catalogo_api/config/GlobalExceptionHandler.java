package com.catalogo.catalogo_api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.catalogo.catalogo_api.util.exeptions.AdminException;
import com.catalogo.catalogo_api.util.exeptions.CardException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<String> handleAdminException(AdminException msg) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg.getMessage());
    } 
    
    @ExceptionHandler(CardException.class)
    public ResponseEntity<String> handleCardException(CardException msg) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg.getMessage());
    } 
}
