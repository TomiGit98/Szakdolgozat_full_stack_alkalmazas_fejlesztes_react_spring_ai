package com.ptechnology.filestorageservice.controller;

import com.ptechnology.filestorageservice.response.ResponseMessage;
import com.ptechnology.filestorageservice.service.FilesStorageServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    Environment environment;

    @Autowired
    private FilesStorageServiceImplementation filesStorageServiceImplementation;

    @Value("${server.port}")
    private int portNumber;


    @CrossOrigin({"http://localhost:3000", "http://localhost:8080"})
    @PostMapping("post/singlee/{id}")
    public Mono<ResponseMessage> uploadPostasd(@PathVariable("id") String id, @RequestPart("upload") Mono<FilePart> filePartMono) {

        return filePartMono
                .doOnNext(filePart -> log.info("Received File: " + filePart.filename()))
                .flatMap(fp -> filesStorageServiceImplementation.init(id).thenReturn(fp))
                .flatMap(fp -> filesStorageServiceImplementation.savePost(fp, id))
                .flatMap(fp -> {
                    log.info("Return file: " + fp);
                    return Mono.just(new ResponseMessage("http://localhost:" + portNumber + "/files/", fp));
                });

    }
}
