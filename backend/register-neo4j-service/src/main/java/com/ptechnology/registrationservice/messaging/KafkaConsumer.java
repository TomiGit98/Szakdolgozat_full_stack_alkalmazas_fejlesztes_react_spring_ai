package com.ptechnology.registrationservice.messaging;

import com.ptechnology.registrationservice.domain.Profilepicture;
import com.ptechnology.registrationservice.domain.User;
import com.ptechnology.registrationservice.repository.UserRepository;
import com.ptechnology.registrationservice.service.NotifyRegistrationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final UserRepository userRepository;
    private final NotifyRegistrationService notifyRegistrationService;

    public KafkaConsumer(UserRepository userRepository, NotifyRegistrationService notifyRegistrationService) {
        this.userRepository = userRepository;
        this.notifyRegistrationService = notifyRegistrationService;
    }

    @KafkaListener(topics = "register_user_to_neo4j")
    public void processMessage(User user) {
        System.out.println("Message received by consumer: " + user.toString());
        user.setReady(true);
        user.setUserid(user.getId());

        Profilepicture profilepicture = new Profilepicture();
        profilepicture.setUserid(user.getUserid());
        profilepicture.setUrl("http://localhost:8300/files/profiledefault/profile.jpg");

        user.setProfilepicture(profilepicture);
        User savedUser = userRepository.save(user);


        if(savedUser != null) {
            notifyRegistrationService.notifyRegistrationSuccessful(user);
        } else {
            notifyRegistrationService.notifyRegistrationFailed(user);
        }
        return;
    }

    @KafkaListener(topics = "successful")
    public void processSuccess(User user) {
        System.out.println("Successful: " + user.toString());
        return;
    }

    @KafkaListener(topics = "insert_not_happened")
    public void processInsertNotHappened(User user) {
        System.out.println("Insert not happened: " + user.toString());
        User usr = userRepository.findUserByEmail(user.getEmail());
        if(usr != null) {
            userRepository.delete(usr);
        }
        return;
    }

    @KafkaListener(topics = "user_deleted")
    public void processUserDeleted(User user) {
        System.out.println("UserDeleted");
        User usr = userRepository.findUserByEmail(user.getEmail());
        if(usr != null) {
            userRepository.delete(user);
        }
        return;
    }

}
