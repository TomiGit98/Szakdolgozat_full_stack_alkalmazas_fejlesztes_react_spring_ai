package com.ptechnology.posthandlingservice.service;

import com.ptechnology.posthandlingservice.model.FileUploadData;
import com.ptechnology.posthandlingservice.model.Postinfo;
import com.ptechnology.posthandlingservice.model.ResponseMessage;
import com.ptechnology.posthandlingservice.model.UserDetails;
import com.ptechnology.posthandlingservice.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PostUploadService {

    private final WebClient.Builder webClientBuilder;
    private final PostRepository postRepository;

    public PostUploadService(WebClient.Builder webClientBuilder, PostRepository postRepository) {
        this.webClientBuilder = webClientBuilder;
        this.postRepository = postRepository;
    }

    public Mono<ResponseMessage> uploadPost(String id, FilePart filePart) {
        System.out.println("make a call!");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("upload", filePart);

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8150/upload/post/singlee/" + id)
                .contentType(MediaType.IMAGE_JPEG)
                .body(BodyInserters.fromMultipartData(body))
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .flatMap(f -> Mono.just(new ResponseMessage(f.getServerName(), f.getFileName())));
    }

    public Mono<UserDetails> getUserData(String id) {
        System.out.println("Get user data!");

        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8400/getuserdetails/" + id)
                .retrieve()
                .bodyToMono(UserDetails.class)
                .flatMap(f -> Mono.just(f));
    }

    public Mono<Postinfo> saveUploadImageDataToDatabase(String id, String serverName, String fileName, String description) {
        return  getUserData(id).flatMap(u -> {
            return postRepository.save(new Postinfo(Long.parseLong(id), fileName, serverName + id + "/", description,u.getProfilephotourl(), u.getUsername()));
        });
    }

    public Mono<Postinfo> saveUploadImageDataToDatabaseTest(Postinfo postinfo) {
        return postRepository.save(new Postinfo(postinfo.getUserid(), postinfo.getFilename(), postinfo.getUrl(), postinfo.getDescription(), postinfo.getProfilephotourl(), postinfo.getUsername()));
    }
}
