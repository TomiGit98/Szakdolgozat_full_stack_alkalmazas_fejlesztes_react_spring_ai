package com.ptechnology.posthandlingservice.controller;

import com.ptechnology.posthandlingservice.model.Postinfo;
import com.ptechnology.posthandlingservice.service.PostHandlingService;
import com.ptechnology.posthandlingservice.service.PostUploadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PostHandlingController {

    private final PostUploadService postUploadService;
    private final KafkaTemplate<String, Postinfo> kafkaTemplate;
    private final PostHandlingService postHandlingService;

    public PostHandlingController(PostUploadService postUploadService, KafkaTemplate<String, Postinfo> kafkaTemplate, PostHandlingService postHandlingService) {
        this.postUploadService = postUploadService;
        this.kafkaTemplate = kafkaTemplate;
        this.postHandlingService = postHandlingService;
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("post/single")
    public Mono<ResponseEntity<String>> postUpload(@RequestHeader("CUSTOM-REQUEST-HEADER") String id,
                                                   @RequestPart("fileToUpload") Mono<FilePart> filePartMono,
                                                   @RequestPart("description") String description) {

        System.out.println("Start");

        System.out.println("Custom Header: " + id.toString());

        return filePartMono.flatMap(f -> postUploadService.uploadPost(id, f))
                .flatMap(f -> postUploadService.saveUploadImageDataToDatabase(id, f.getServerName(), f.getFileName(), description))
                .flatMap(postinfo -> {
                    System.out.println("Postinfo: " + postinfo.toString());
                    kafkaTemplate.send("generate_feed", postinfo);
                    return Mono.empty();
                })
                .flatMap(f -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Access-Control-Allow-Credentials", "true");
                    return Mono.just(new ResponseEntity<>("Ok", headers, HttpStatus.OK));
                });
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("post/all")
    public Flux<Postinfo> getListFiles(@RequestHeader("CUSTOM-REQUEST-HEADER") String id) {
        System.out.println("post/all");
        return postHandlingService.loadUserAllPost(id);
    }

}
