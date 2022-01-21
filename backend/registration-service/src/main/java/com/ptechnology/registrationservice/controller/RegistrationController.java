package com.ptechnology.registrationservice.controller;

import com.ptechnology.registrationservice.domain.User;
import com.ptechnology.registrationservice.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;
    private final KafkaTemplate<String, User> kafkaTemplate;

    public RegistrationController(RegistrationService registrationService, KafkaTemplate<String, User> kafkaTemplate) {
        this.registrationService = registrationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user){
        System.out.println(user.toString());
        String result = registrationService.registerUser(user);
        if(result.equals("WrongCredentials")){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        } else if(result.equals("UserAlreadyExists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
