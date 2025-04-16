package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@SpringBootApplication
public class TemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
		System.out.println("muri");
	}

}
