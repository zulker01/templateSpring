package com.example.template_spring.Repository;

import com.example.template_spring.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
