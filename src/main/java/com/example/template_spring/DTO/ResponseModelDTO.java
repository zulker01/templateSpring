package com.example.template_spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModelDTO<T> {
    private String status;          // success / error
    private String message;         // human-readable message
    private T data;                  // payload
    private String timestamp;        // ISO time
    private String correlationId;    // for tracking
    private String errorCode;        // optional for errors

    public static <T> ResponseModelDTO<T> success(T data, String message) {
        return ResponseModelDTO.<T>builder()
                .status("success")
                .message(message)
                .data(data)
                .timestamp(Instant.now().toString())
                .correlationId(UUID.randomUUID().toString())
                .build();
    }

    public static <T> ResponseModelDTO<T> error(String message, String errorCode) {
        return ResponseModelDTO.<T>builder()
                .status("error")
                .message(message)
                .errorCode(errorCode)
                .data(null)
                .timestamp(Instant.now().toString())
                .correlationId(UUID.randomUUID().toString())
                .build();
    }
} 