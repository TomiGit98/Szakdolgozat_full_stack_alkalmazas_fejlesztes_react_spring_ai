package com.ptechnology.aiauthfileservice.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FilesStorageServiceImplementation implements FilesStorageServiceInterface {


    @Override
    public Mono<Void> init(String id) {
        //String path = "files/" + id;
        String path = "files/";
        return Mono.just(new File(path))
                .flatMap(f -> {
                    if (!Files.exists(Paths.get(path))) {
                        f.mkdir();
                    }
                    return Mono.empty();
                })
                .then();
    }

    public Mono<Void> initAuthenticationLib() {
        //String path = "files/" + id;
        String path = "auth/";
        return Mono.just(new File(path))
                .flatMap(f -> {
                    if (!Files.exists(Paths.get(path))) {
                        f.mkdir();
                    }
                    return Mono.empty();
                })
                .then();
    }

    @Override
    public Mono<String> savePost(FilePart file, String id, String blob) {
        UUID uuid = UUID.randomUUID();
        return Mono.just(
                //file.transferTo(Paths.get("./files/" + id + "/")
                file.transferTo(Paths.get("./files/")
                        ////.resolve(uuid + "_" + file.filename()))
                        .resolve(id + "_" + uuid + "_" + file.filename() + blob))
                        .subscribe())
                .thenReturn(id + "_" + uuid + "_" + file.filename());
    }


    public Mono<String> saveAuthenticationPhoto(FilePart file, String blob) {
        UUID uuid = UUID.randomUUID();
        return Mono.just(
                file.transferTo(Paths.get("./auth/")
                        .resolve(uuid + "_" + file.filename() + blob))
                        .subscribe())
                .thenReturn(uuid + "_" + file.filename() + blob);
    }



    @Override
    public Mono<Resource> load(String id, String filename) throws MalformedURLException {
        //Path file = root.resolve(filename);
        Path file = Paths.get("./files/" + id + "/").resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return Mono.just(resource);
        } else {
            throw new RuntimeException("File not found!");
        }
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }
}
