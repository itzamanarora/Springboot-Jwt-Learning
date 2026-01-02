package com.example.jwt.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JwtLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtLearningApplication.class, args);
	}

}
