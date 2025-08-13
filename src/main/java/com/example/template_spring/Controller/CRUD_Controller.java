package com.example.template_spring.Controller;

import com.example.template_spring.DTO.UserDTO;
import com.example.template_spring.DTO.ResponseModelDTO;
import com.example.template_spring.Entity.User;
import com.example.template_spring.Service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CRUD_Controller {
  private final UserService userService;

@GetMapping("/api/hello")
public ResponseEntity<ResponseModelDTO<String>> hello() {
    try {
        return ResponseEntity.ok(ResponseModelDTO.success("Hello, World!", "Greeting message"));
    } catch (Exception e) {
        throw new RuntimeException("An error occurred: " + e.getMessage());
    }
}

@PostMapping("/api/user")
public ResponseEntity<ResponseModelDTO<UserDTO>> createUser(@RequestBody @Valid UserDTO userDTO) {
    try {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(ResponseModelDTO.success(createdUser, "User created successfully"));
    } catch (Exception e) {
      throw new RuntimeException("An error occurred: " + e.getMessage());
    }
}

@GetMapping("/api/users/all")
public ResponseEntity<ResponseModelDTO<List<UserDTO>>> getAllUser() {
    try {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(ResponseModelDTO.success(users, "Users retrieved successfully"));
    } catch (Exception e) {
      throw new RuntimeException("An error occurred: " + e.getMessage());
    }
}
}
