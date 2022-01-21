package com.ptechnology.posthandlingservice.messaging;

import com.ptechnology.posthandlingservice.model.Postinfo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    //private final KafkaTemplate<String, Postinfo> kafkaTemplate;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    //public KafkaConsumer(PostRepository postRepository, KafkaTemplate<String, Postinfo> kafkaTemplate) {
    public KafkaConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "generate_feed", containerFactory = "kafkaListenerContainerFactory")
    public void processAddToFeed(Postinfo postinfo) {
        // Get Friends List
        // friends
        System.out.println("Generate Feeds");
        kafkaTemplate.send("get_friends_to_post_generation", postinfo);

    }
}
