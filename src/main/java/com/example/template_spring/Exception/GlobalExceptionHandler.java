package com.example.template_spring.Exception;

import com.example.template_spring.DTO.ResponseModelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.security.SignatureException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModelDTO<Object>> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseModelDTO.error(ex.getMessage(), "INTERNAL_ERROR"));
    }
    @ExceptionHandler({SignatureException.class, ExpiredJwtException.class, MalformedJwtException.class})
    public ResponseEntity<ResponseModelDTO<Object>> handleJwtExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseModelDTO.error(ex.getMessage(), "INVALID_JWT"));
    }
} 