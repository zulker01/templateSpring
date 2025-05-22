package com.example.template_spring.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User
{
  @Id
  @Column(name = "id",nullable = false)
  private String id;

  @Column(name = "username",nullable = false)
  private String username;

  @Column(name = "password",nullable = false)
  private String password;


}
