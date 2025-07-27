package com.example.template_spring.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow the desired HTTP methods
            .allowedHeaders("*")  // Allow all headers
            .allowCredentials(true)  // Allow credentials (cookies, sessions, etc.)
            .maxAge(3600);  // Cache pre-flight response for 1 hour
  }
}

