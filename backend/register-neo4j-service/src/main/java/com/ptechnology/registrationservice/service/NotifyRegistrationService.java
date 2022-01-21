package com.ptechnology.registrationservice.service;

import com.ptechnology.registrationservice.domain.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotifyRegistrationService {

    private static final String TOPIC_SUCCESS = "notify_registration_success";
    private static final String TOPIC_FAIL = "notify_registration_fail";

    private final KafkaTemplate<String, User> kafkaTemplate;


    public NotifyRegistrationService(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notifyRegistrationSuccessful(User user) {
        System.out.println("Registration Successful!");
        kafkaTemplate.send(TOPIC_SUCCESS, user);
        return;
    }

    public void notifyRegistrationFailed(User user) {
        System.out.println("Registration Failed!");
        kafkaTemplate.send(TOPIC_FAIL, user);
        return;
    }
}
