package com.ptechnology.posthandlingservice.messaging;

import com.ptechnology.posthandlingservice.model.Postinfo;
import com.ptechnology.posthandlingservice.model.User;
import com.ptechnology.posthandlingservice.model.UserAndPostinfo;
import com.ptechnology.posthandlingservice.model.Userpostinfo;
import com.ptechnology.posthandlingservice.repository.UserRepository;
import com.ptechnology.posthandlingservice.service.PostGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class KafkaUserAndPostinfoConsumer {

    @Autowired
    private KafkaTemplate<String, UserAndPostinfo> userAndPostinfoKafkaTemplate;

    @Autowired
    private PostGenerationService postGenerationService;

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "add_to_feed")
    public Mono<Userpostinfo> processAddToFeed(UserAndPostinfo userAndPostinfo) {
        List<User> friends = userAndPostinfo.getUser().getFriends();
        friends.add(userAndPostinfo.getUser());
        System.out.println("user-query");
        System.out.println(friends.toString());

        Flux.fromIterable(friends).flatMap(f -> {
            System.out.println("Ez egy firend: " + f.toOutString());
            UserAndPostinfo userAndPostinfo1 = new UserAndPostinfo(f, userAndPostinfo.getPostinfo());
            postGenerationService.addFeedToUser(userAndPostinfo1).subscribe();
            return Mono.empty();
        }).subscribe();
        return Mono.empty();
    }
}
