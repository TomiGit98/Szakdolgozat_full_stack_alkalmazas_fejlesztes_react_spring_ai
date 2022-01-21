package com.ptechnology.posthandlingservice.service;

import com.ptechnology.posthandlingservice.model.Postinfo;
import com.ptechnology.posthandlingservice.repository.PostRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PostHandlingService {

    private final PostRepository postRepository;

    public PostHandlingService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Flux<Postinfo> loadUserAllPost(String id) {
        return postRepository.findPostinfoByUserid(id);
    }
}
