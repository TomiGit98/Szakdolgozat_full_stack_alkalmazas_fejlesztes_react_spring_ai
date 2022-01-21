package com.ptechnology.aiauthfileservice.controller;

import com.ptechnology.aiauthfileservice.service.FilesStorageServiceImplementation;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;

@RestController
public class DownloadController {

    private final FilesStorageServiceImplementation filesStorageServiceImplementation;

    public DownloadController(FilesStorageServiceImplementation filesStorageServiceImplementation) {
        this.filesStorageServiceImplementation = filesStorageServiceImplementation;
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("files/{id}/{filename}")
    @ResponseBody
    public Mono<Resource> getFile(@PathVariable("id") String id, @PathVariable("filename") String filename) throws MalformedURLException {
        return filesStorageServiceImplementation.load(id, filename);
    }

}
