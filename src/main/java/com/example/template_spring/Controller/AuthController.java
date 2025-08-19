package com.example.template_spring.Controller;


import com.example.template_spring.DTO.LoginRequestDTO;
import com.example.template_spring.DTO.LoginResponseDTO;
import com.example.template_spring.DTO.ResponseModelDTO;
import com.example.template_spring.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<ResponseModelDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authService.login(loginRequest);
            return ResponseEntity.ok(ResponseModelDTO.success(response, "Login successful"));
        } catch (Exception e) {
            // This will be caught by GlobalExceptionHandler
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }
} 