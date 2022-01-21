package com.ptechnology.aiauthfileservice.service;

import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;

public interface FilesStorageServiceInterface {

    public Mono<Void> init(String id);

    public Mono<String> savePost(FilePart file, String id, String blob);

    public Mono<Resource> load(String id, String filename) throws MalformedURLException;

    public Mono<Void> deleteAll();

    //public Flux<Fileinfo> loadAll(String id);

    //public Flux<Postinfo> loadAllPost(String id);
}
