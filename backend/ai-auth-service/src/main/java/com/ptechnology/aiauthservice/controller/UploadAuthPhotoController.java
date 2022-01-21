package com.ptechnology.aiauthservice.controller;

import com.ptechnology.aiauthservice.repository.AuthInfoRepository;
import com.ptechnology.aiauthservice.service.AuthInfoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
public class UploadAuthPhotoController {

    @Autowired
    AuthInfoRepository authInfoRepository;

    @Autowired
    AuthInfoUploadService authInfoUploadService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("auth")
    public Mono<ResponseEntity<String>> postUpload(@RequestHeader("CUSTOM-REQUEST-HEADER") String id,
                                                   //@PathVariable("id") String id,
                                                   @RequestPart("fileToUpload") Mono<FilePart> filePartMono,
                                                   @RequestPart("blob") String blobTo) {


        System.out.println("Start");

        System.out.println("Custom Header: " + id.toString());

        System.out.println("Custom Header: " + filePartMono);

        String blobToSave;
        if(blobTo.equals("not")){
            blobToSave = "";
        } else {
            blobToSave = ".jpg";
        }

        return filePartMono.flatMap(f -> {
            return authInfoUploadService.uploadPost(id, f, blobTo);
        })
                .flatMap(f -> authInfoUploadService.saveUploadImageDataToDatabase(id, f.getServerName(), f.getFileName()+blobToSave))
                .flatMap(f -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Access-Control-Allow-Credentials", "true");
                    System.out.println("Itt vagy");
                    return Mono.just(new ResponseEntity<>("Ok", headers, HttpStatus.OK));
                });
    }
}
