package com.project.Kdemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.Kdemy.dto.SignupRequest;
import com.project.Kdemy.model.User;
import com.project.Kdemy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdemyApplication.class, args);
	}


}
