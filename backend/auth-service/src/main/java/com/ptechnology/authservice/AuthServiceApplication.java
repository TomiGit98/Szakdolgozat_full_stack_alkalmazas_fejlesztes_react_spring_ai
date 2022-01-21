package com.ptechnology.authservice;

import com.ptechnology.authservice.domain.Role;
import com.ptechnology.authservice.domain.User;
import com.ptechnology.authservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@EnableEurekaClient
@SpringBootApplication
public class AuthServiceApplication {

	/*

	 http://localhost:xxxx/api/token/refresh
	 - GET
	Headers:
	Authorization: Bearer refreshtoken

	 http://localhost:xxxx/api/users
	 - GET
	 Headers:
	 Authorization: Bearer accesstoken


	http://localhost:xxxx/api/login
	- POST
    Body:
    username: ...
    password: ...


	 */

	/*
	Stat application
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	/*
	Password Encoder to hash password
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	Default values to database
	 */
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			System.out.println("Commandline runner done!");
		};
	}
}
