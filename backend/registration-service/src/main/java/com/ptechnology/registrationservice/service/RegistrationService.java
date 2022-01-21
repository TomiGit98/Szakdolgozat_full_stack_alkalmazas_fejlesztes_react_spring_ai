package com.ptechnology.registrationservice.service;

import com.ptechnology.registrationservice.domain.User;
import com.ptechnology.registrationservice.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private static final String TOPIC = "register_user_to_neo4j";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final KafkaTemplate<String, User> kafkaTemplate;

    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository, KafkaTemplate<String, User> kafkaTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public String registerUser(User user) {
        if(user.getEmail() != "" && user.getUsername() != "" && user.getPassword() != ""){

            User u = userRepository.findByEmail(user.getEmail());
            if(u == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setReady(false);
                User createdReturn = userRepository.save(user);
                System.out.println("CreatedUser: " + createdReturn.toString());
                kafkaTemplate.send(TOPIC, createdReturn);
                return "Registered";
            }else {
                return "UserAlreadyExists";
            }
        }else{
            return "WrongCredentials";
        }
    }
}
