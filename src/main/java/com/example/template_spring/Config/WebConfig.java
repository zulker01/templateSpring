package com.example.template_spring.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Value("${frontend.url}")
  private String frontendUrl;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // Allow all origins (you can restrict it to specific origins)
    registry.addMapping("/**")
            .allowedOrigins(frontendUrl)  // Add your frontend URL here
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
            .maxAge(3600);  // Cache pre-flight response for 1 hour
  }
  //gpt suggested this bean to be in security config , but it created a circular dependency issue
  // so moved it to web config
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

