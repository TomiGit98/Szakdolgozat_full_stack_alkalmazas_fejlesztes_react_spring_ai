package com.ptechnology.registrationservice.messaging;

import com.ptechnology.registrationservice.domain.User;
import com.ptechnology.registrationservice.repository.UserRepository;
import com.ptechnology.registrationservice.service.CheckUserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final UserRepository userRepository;
    private final CheckUserService checkUserService;
    private final KafkaTemplate<String, User> kafkaTemplate;

    public KafkaConsumer(UserRepository userRepository, CheckUserService checkUserService, KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.userRepository = userRepository;
        this.checkUserService = checkUserService;
    }

    @KafkaListener(topics = "notify_registration_success")
    public void processMessageOnSuccess(User user) {
        System.out.println("OnSuccess: " + user.toString());
        checkUserService.checkUser(user);
        return;
    }

    @KafkaListener(topics = "notify_registration_fail")
    public void processMessageOnFail(User user) {
        System.out.println("OnFail: " + user.toString());
        User usr = userRepository.findByEmail(user.getEmail());
        if (usr != null) {
            userRepository.delete(user);
        }
        kafkaTemplate.send("user_deleted", user);
        System.out.println("UserDeleted");
        return;
    }

}
