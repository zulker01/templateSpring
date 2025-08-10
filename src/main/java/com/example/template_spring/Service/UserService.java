package com.example.template_spring.Service;

import com.example.template_spring.DTO.UserDTO;
import com.example.template_spring.Entity.User;
import com.example.template_spring.Repository.UserRepository;
import com.example.template_spring.Service.CustomUserDetails;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true,new ArrayList<>());
  }

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
