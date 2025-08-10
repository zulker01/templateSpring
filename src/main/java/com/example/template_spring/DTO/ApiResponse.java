package com.example.template_spring.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String error;
    private LocalDateTime timestamp;
    private int statusCode;

    // Success response constructor
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, null, LocalDateTime.now(), 200);
    }

    // Success response with default message
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Operation completed successfully", data, null, LocalDateTime.now(), 200);
    }

    // Error response constructor
    public static <T> ApiResponse<T> error(String error, int statusCode) {
        return new ApiResponse<>(false, null, null, error, LocalDateTime.now(), statusCode);
    }

    // Error response with message
    public static <T> ApiResponse<T> error(String error, String message, int statusCode) {
        return new ApiResponse<>(false, message, null, error, LocalDateTime.now(), statusCode);
    }
} 