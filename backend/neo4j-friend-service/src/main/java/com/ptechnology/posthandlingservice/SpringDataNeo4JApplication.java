package com.ptechnology.posthandlingservice;

import com.ptechnology.posthandlingservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class SpringDataNeo4JApplication {

    private final static Logger log = LoggerFactory.getLogger(SpringDataNeo4JApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringDataNeo4JApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(UserRepository userRepository) {
        return args -> {

            userRepository.deleteAll();
        };
    }



}
