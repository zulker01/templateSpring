package com.example.template_spring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRUD_Controller {

  @GetMapping("/api/hello")
  public String hello() {
    return "Hello, World!";
  }

  @PostMapping("/api/user")
  public String createUser() {
    // Logic to create a user
    return "User created successfully!";
  }
}
