package com.ptechnology.posthandlingservice;

import com.ptechnology.posthandlingservice.model.Postinfo;
import com.ptechnology.posthandlingservice.model.User;
import com.ptechnology.posthandlingservice.model.UserAndPostinfo;
import com.ptechnology.posthandlingservice.model.Userpostinfo;
import com.ptechnology.posthandlingservice.repository.UserRepository;
import com.ptechnology.posthandlingservice.service.PostGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    KafkaTemplate<String, Object> postinfoKafkaTemplate;

    @Autowired
    PostGenerationService postGenerationService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public Flux<Postinfo> test(@RequestBody Postinfo postinfo) {
        postinfoKafkaTemplate.send("get_friends_to_post_generation", postinfo);
        Postinfo p1 = new Postinfo( 1L, "hal1i", "hó", "hal1i", "a", "a");
        Postinfo p2 = new Postinfo( 1L, "hal3i", "hó1", "hal412i", "b", "b");
        Postinfo p3 = new Postinfo( 1L, "hal4i", "hó23", "hali14", "c", "c");
        return Flux.just(p1, p2, p3);
    }


    @PutMapping("/del")
    public Mono<Userpostinfo> testerem(@RequestBody Postinfo postinfo) {
        userRepository.deleteAll().subscribe();
        /*userRepository.save(new Userpostinfo(15L, List.of(
                new Postinfo(null, 15L, "asdf", "sdf", "dfs"),
        ))).subscribe();
        userRepository.save(new Userpostinfo(16L, List.of(postinfo, postinfo, postinfo, postinfo, postinfo, postinfo))).subscribe();*/
        return Mono.empty();
    }

    @PutMapping
    public Mono<Userpostinfo> tester(@RequestBody Postinfo postinfo) {
        return postGenerationService.addFeedToUser(new UserAndPostinfo(new User(postinfo.getUserid(), 1L, "", "", "", true, null, null), postinfo));
    }
}
