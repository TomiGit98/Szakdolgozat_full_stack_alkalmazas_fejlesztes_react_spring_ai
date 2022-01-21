package com.ptechnology.fileservergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileServerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileServerGatewayApplication.class, args);
    }

}
