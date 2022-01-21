package com.ptechnology.aiauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AiAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAuthServiceApplication.class, args);
    }

}
