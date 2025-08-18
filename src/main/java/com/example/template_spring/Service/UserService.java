package com.example.template_spring.Service;

import com.example.template_spring.DTO.UserDTO;
import com.example.template_spring.Entity.User;
import com.example.template_spring.Repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserDTO getUserById(String id) {
    User user =  userRepository.findById(id).orElse(null);
    return new UserDTO(user.getId(), user.getUsername(),user.getPassword());
  }
  public UserDTO createUser(UserDTO userDTO) {
    User user = new User();
    user.setId(userDTO.getId());
    user.setUsername(userDTO.getUsername());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    userRepository.save(user);
    return userDTO;
  }

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword()))
                .toList();
  }
  public UserDTO updateUser(String id, UserDTO userDTO) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      user.setUsername(userDTO.getUsername());
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
      userRepository.save(user);
    }
    return userDTO;
  }
  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }

}
