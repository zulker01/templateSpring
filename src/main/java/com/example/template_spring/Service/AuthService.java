package com.example.template_spring.Service;

import com.example.template_spring.DTO.LoginRequestDTO;
import com.example.template_spring.DTO.LoginResponseDTO;
import com.example.template_spring.Entity.User;
import com.example.template_spring.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );
        
        // Get user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Generate JWT token
        String token = jwtUtils.generateToken(userDetails);
        
        // Get user entity for additional info
        User user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        return new LoginResponseDTO(
            token,
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getType()
        );
    }
    
    public boolean validateCredentials(String username, String rawPassword) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
} 