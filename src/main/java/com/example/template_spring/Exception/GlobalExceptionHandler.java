package com.example.template_spring.Exception;

import com.example.template_spring.DTO.ResponseModelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseModelDTO<Object>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseModelDTO.error(ex.getMessage(), "USER_NOT_FOUND"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseModelDTO<Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseModelDTO.error("Invalid parameter: " + ex.getName(), "INVALID_PARAM"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModelDTO<Object>> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseModelDTO.error(ex.getMessage(), "INTERNAL_ERROR"));
    }
} 