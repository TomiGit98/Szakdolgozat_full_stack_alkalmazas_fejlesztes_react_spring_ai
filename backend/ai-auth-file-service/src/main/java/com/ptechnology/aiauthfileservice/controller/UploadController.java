package com.ptechnology.aiauthfileservice.controller;

import com.ptechnology.aiauthfileservice.response.ResponseMessage;
import com.ptechnology.aiauthfileservice.service.FilesStorageServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("upload")
public class UploadController {

    @Value("${server.port}")
    private int portNumber;

    @Autowired
    private FilesStorageServiceImplementation filesStorageServiceImplementation;

    @CrossOrigin({"http://localhost:3000", "http://localhost:8080"})
    @PostMapping("authphoto/singlee/{id}/{blob}")
    public Mono<ResponseMessage> uploadPostasd(@PathVariable("id") String id, @RequestPart("upload") Mono<FilePart> filePartMono, @PathVariable("blob") String blob) {

        String blobToSave;
        if(blob.equals("not")){
            blobToSave = "";
        } else {
            blobToSave = ".jpg";
        }

        return filePartMono
                .doOnNext(filePart -> {
                    log.info("Received File: " + filePart.filename());
                })
                .flatMap(fp -> filesStorageServiceImplementation.init(id).thenReturn(fp))
                .flatMap(fp -> filesStorageServiceImplementation.savePost(fp, id, blobToSave))
                .flatMap(fp -> {
                    log.info("Return file: " + fp);
                    return Mono.just(new ResponseMessage("files/", fp));
                });
    }
}
