package com.ptechnology.posthandlingservice.messaging;

import com.ptechnology.posthandlingservice.model.Postinfo;
import com.ptechnology.posthandlingservice.model.User;
import com.ptechnology.posthandlingservice.model.UserAndPostinfo;
import com.ptechnology.posthandlingservice.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserAndPostinfo> kafkaTemplate;

    public KafkaConsumer(UserRepository userRepository, KafkaTemplate<String, UserAndPostinfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "get_friends_to_post_generation")
    public void getUserAndFriendsToPostGeneration(Postinfo postinfo) {
        User user = userRepository.findByUseridEquals(postinfo.getUserid());

        if(user != null){
            System.out.println("My user: " + user.toString());

            kafkaTemplate.send("add_to_feed", new UserAndPostinfo(user, postinfo));
        } else {
            System.out.println("Üres a user");
            kafkaTemplate.send("add_to_feed", new UserAndPostinfo(user, postinfo));
        }

        return;
    }
}