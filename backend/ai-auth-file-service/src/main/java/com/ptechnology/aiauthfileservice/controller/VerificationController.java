package com.ptechnology.aiauthfileservice.controller;

import com.ptechnology.aiauthfileservice.response.ResponseMessage;
import com.ptechnology.aiauthfileservice.service.FilesStorageServiceImplementation;
import com.ptechnology.aiauthfileservice.service.VerifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@RestController
public class VerificationController {

    @Autowired
    VerifyService verifyService;

    @Autowired
    private FilesStorageServiceImplementation filesStorageServiceImplementation;

    @CrossOrigin({"http://localhost:3000", "http://localhost:8080"})
    @PostMapping("auth/verify/{blob}")
    public Mono<ResponseMessage> uploadPostasd(@RequestPart("upload") Mono<FilePart> filePartMono,  @PathVariable("blob") String blob) {

        String blobToSave = ".jpg";

        return filePartMono
                .doOnNext(filePart -> {
                    log.info("Received File: " + filePart.filename());
                })
                .flatMap(fp -> filesStorageServiceImplementation.initAuthenticationLib().thenReturn(fp))
                .flatMap(fp -> filesStorageServiceImplementation.saveAuthenticationPhoto(fp, blobToSave))
                .flatMap(fp -> {
                    try {
                        return Mono.just(verifyService.runPythonScript("auth/" + fp, "files"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Mono.just(-1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return Mono.just(-1);
                    }
                })
                .flatMap(fp -> {

                    log.info("Return userid: " + fp);
                    return Mono.just(new ResponseMessage("files/", fp.toString()));
                });
    }

    @GetMapping("/{img1}/{img2}")
    public void verify(@PathVariable("img1") String img1, @PathVariable("img2") String img2) throws IOException, InterruptedException {
        System.out.println("IMG1: " + img1 + " " + "IMG2: " + img2);
        verifyService.runPythonScript("files/1/148367ef-6544-46c0-91f6-86c87890adb9_snapshot.jpg", "files/1/c2e017b4-2147-445f-b514-2627ecb9722c_snapshot.jpg");
    }

}
