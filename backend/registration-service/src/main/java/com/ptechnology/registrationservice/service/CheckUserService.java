package com.ptechnology.registrationservice.service;

import com.ptechnology.registrationservice.domain.User;
import com.ptechnology.registrationservice.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckUserService {

    private static final String TOPIC = "success";

    private final UserRepository userRepository;
    private final KafkaTemplate<String, User> kafkaTemplate;

    public CheckUserService(UserRepository userRepository, KafkaTemplate<String, User> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public void checkUser(User user) {
        User userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail != null) {
            userRepository.setReadyByUserId(true, userByEmail.getId());
            kafkaTemplate.send("successful", user);
        } else {
            kafkaTemplate.send("insert_not_happened", user);
        }
        return;
    }
}
