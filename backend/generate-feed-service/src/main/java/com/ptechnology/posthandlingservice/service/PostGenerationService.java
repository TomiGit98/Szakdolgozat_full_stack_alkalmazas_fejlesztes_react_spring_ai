package com.ptechnology.posthandlingservice.service;

import com.ptechnology.posthandlingservice.model.UserAndPostinfo;
import com.ptechnology.posthandlingservice.model.Userpostinfo;
import com.ptechnology.posthandlingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostGenerationService {

    private int postContainerSize = 10;

    @Autowired
    private UserRepository userRepository;

    public Mono<Userpostinfo> addFeedToUser(UserAndPostinfo userAndPostinfo) {
        return Mono.just(userAndPostinfo)
                .flatMap(f -> {
                    System.out.println("ITT");
                    System.out.println(userAndPostinfo.getUser().getUserid());
                    System.out.println(userAndPostinfo.getUser().getId());

                    userRepository.findByUseridEquals(userAndPostinfo.getUser().getUserid())
                            .flatMap(f1 -> {
                                if (f1.getPostinfos().size() < postContainerSize) {
                                    f1.getPostinfos().add(userAndPostinfo.getPostinfo());
                                    System.out.println("Append");
                                    return userRepository.save(f1);
                                } else {
                                    int deleteFirstN = (f1.getPostinfos().size()-(postContainerSize-1));
                                    f1.getPostinfos().removeAll(f1.getPostinfos().stream().limit(deleteFirstN).collect(Collectors.toList()));
                                    f1.getPostinfos().add(userAndPostinfo.getPostinfo());
                                    System.out.println("New");
                                    return userRepository.save(f1);
                                }
                            })
                            .switchIfEmpty(userRepository.save(new Userpostinfo(null, userAndPostinfo.getUser().getUserid(), List.of(userAndPostinfo.getPostinfo()))))
                            .subscribe();
                    System.out.println("END ITT");
                    return Mono.just(new Userpostinfo());
                });
    }

}
