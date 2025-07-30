package com.example.template_spring.Controller;

import com.example.template_spring.DTO.UserDTO;
import com.example.template_spring.Entity.User;
import com.example.template_spring.Service.UserService;

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
  public String hello() {
    return "Hello, World!";
  }

  @PostMapping("/api/user")
  public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
    UserDTO createdUser= userService.createUser(userDTO);
    return createdUser;
  }

  @GetMapping("/api/users/all")
  public List<UserDTO> getAllUser() {
    return userService.getAllUsers();
  }
}
