package com.ptechnology.feedservice.controller;

import com.ptechnology.feedservice.model.Postinfo;
import com.ptechnology.feedservice.model.Userpostinfo;
import com.ptechnology.feedservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FeedController {

    private final UserRepository userRepository;

    public FeedController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/feed")
    public Mono<ResponseEntity<Userpostinfo>> getUserFeeds(@RequestHeader("CUSTOM-REQUEST-HEADER") String id) {
        return userRepository.findByUseridEquals(Long.parseLong(id))
                .flatMap(u -> Mono.just(ResponseEntity.status(HttpStatus.OK).body(u)))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }
}
