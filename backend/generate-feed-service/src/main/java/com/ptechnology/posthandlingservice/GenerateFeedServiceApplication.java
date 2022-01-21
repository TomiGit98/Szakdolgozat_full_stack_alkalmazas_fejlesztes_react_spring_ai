package com.ptechnology.posthandlingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GenerateFeedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateFeedServiceApplication.class, args);
    }

}
