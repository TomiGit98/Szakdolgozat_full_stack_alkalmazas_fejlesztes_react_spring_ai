package com.ptechnology.aiauthservice.service;

import com.ptechnology.aiauthservice.model.Authinfo;
import com.ptechnology.aiauthservice.model.ResponseMessage;
import com.ptechnology.aiauthservice.model.TokenResponseMessage;
import com.ptechnology.aiauthservice.repository.AuthInfoRepository;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthInfoUploadService {

    private final WebClient.Builder webClientBuilder;
    private final AuthInfoRepository authInfoRepository;

    public AuthInfoUploadService(WebClient.Builder webClientBuilder, AuthInfoRepository authInfoRepository) {
        this.webClientBuilder = webClientBuilder;
        this.authInfoRepository = authInfoRepository;
    }

    public Mono<ResponseMessage> verifyPhoto(Object filePart, String blob) {
        System.out.println("make a call!");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("upload", filePart);

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8600/auth/verify/" + blob)
                .contentType(MediaType.IMAGE_JPEG)
                .body(BodyInserters.fromMultipartData(body))
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .flatMap(f -> Mono.just(new ResponseMessage(f.getServerName(), f.getFileName())));
    }


    public Mono<ResponseMessage> uploadPost(String id, Object filePart, String blob) {
        System.out.println("make a call!");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("upload", filePart);

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8600/upload/authphoto/singlee/" + id + "/" + blob)
                .contentType(MediaType.IMAGE_JPEG)
                .body(BodyInserters.fromMultipartData(body))
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .flatMap(f -> Mono.just(new ResponseMessage(f.getServerName(), f.getFileName())));
    }

    public Mono<TokenResponseMessage> getTokens(String id) {
        System.out.println("make a call get tokens!");

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8099/api/userbyid")
                .body(BodyInserters.fromValue(id))
                .retrieve()
                .bodyToMono(TokenResponseMessage.class)
                .flatMap(f -> Mono.just(new TokenResponseMessage(f.getAccessToken(), f.getRefreshToken())));
    }

    public Mono<Authinfo> saveUploadImageDataToDatabase(String id, String serverName, String fileName) {
        return authInfoRepository.save(new Authinfo(Long.parseLong(id), fileName, serverName + id + "/"));
    }
}
