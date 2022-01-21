package com.ptechnology.filestorageservice.service;

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
        String path = "files/" + id;
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
    public Mono<String> savePost(FilePart file, String id) {
        UUID uuid = UUID.randomUUID();
        return Mono.just(
                file.transferTo(Paths.get("./files/" + id + "/")
                        .resolve(uuid + "_" + file.filename()))
                        .subscribe())
                .thenReturn(uuid + "_" + file.filename());
    }

    @Override
    public Mono<Resource> load(String id, String filename) throws MalformedURLException {
        //Path file = root.resolve(filename);
        Path file = Paths.get("./files/" + id + "/").resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return Mono.just(resource);
        } else {
            throw new RuntimeException("file not found");
        }
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }
}
