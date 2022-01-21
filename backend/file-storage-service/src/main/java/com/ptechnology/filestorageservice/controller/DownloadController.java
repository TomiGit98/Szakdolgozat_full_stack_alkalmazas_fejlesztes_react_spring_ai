package com.ptechnology.filestorageservice.controller;

import com.ptechnology.filestorageservice.service.FilesStorageServiceImplementation;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
