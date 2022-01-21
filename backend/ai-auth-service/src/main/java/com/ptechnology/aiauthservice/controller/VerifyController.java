package com.ptechnology.aiauthservice.controller;

import ch.qos.logback.core.subst.Token;
import com.ptechnology.aiauthservice.model.TokenResponseMessage;
import com.ptechnology.aiauthservice.service.AuthInfoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class VerifyController {

    @Autowired
    AuthInfoUploadService authInfoUploadService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("verify")
    public Mono<ResponseEntity<TokenResponseMessage>> postUpload(@RequestPart("fileToUpload") Mono<FilePart> filePartMono,
                                                   @RequestPart("blob") String blobTo) {

        System.out.println("Start");

        System.out.println("Custom Header: " + filePartMono);

        System.out.println("BLOB: " + blobTo);

        String blobToSave;
        if(blobTo.equals("not")){
            blobToSave = "";
        } else {
            blobToSave = ".jpg";
        }

        return filePartMono.flatMap(f -> {
            return authInfoUploadService.verifyPhoto(f, blobTo);
        })
                .flatMap(f -> {
                    System.out.println("userid: " + f.getFileName());
                    return Mono.just(f);
                })
                .flatMap(f -> {
                    System.out.println("USR: " + f.getFileName());
                    if(!(f.getFileName().toString().equals("-1"))) {
                        return authInfoUploadService.getTokens(f.getFileName())
                            .flatMap(res -> {
                                System.out.println("access: " + res.getAccessToken());
                                System.out.println("refresh: " + res.getRefreshToken());
                                HttpHeaders headers = new HttpHeaders();
                                headers.add("Access-Control-Allow-Credentials", "true");
                                //return Mono.just(new ResponseEntity<>(new TokenResponseMessage(res.getAccessToken(), res.getRefreshToken()), headers, HttpStatus.OK));
                                return Mono.just(new ResponseEntity<TokenResponseMessage>(new TokenResponseMessage(res.getAccessToken(), res.getRefreshToken()), HttpStatus.OK));
                                //return Mono.just(new ResponseEntity<TokenResponseMessage>);
                            });
                    } else {
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Access-Control-Allow-Credentials", "true");
                        return Mono.just(new ResponseEntity<>(new TokenResponseMessage("-", "-"), headers, HttpStatus.OK));
                    }
                });
    }
}
