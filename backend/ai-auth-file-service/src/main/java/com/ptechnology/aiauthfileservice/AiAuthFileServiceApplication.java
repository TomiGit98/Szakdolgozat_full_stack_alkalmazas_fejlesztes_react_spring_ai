package com.ptechnology.aiauthfileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AiAuthFileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAuthFileServiceApplication.class, args);
    }

}
