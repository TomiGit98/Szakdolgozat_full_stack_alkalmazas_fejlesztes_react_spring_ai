package com.ptechnology.posthandlingservice;

import com.ptechnology.posthandlingservice.model.Postinfo;
import com.ptechnology.posthandlingservice.repository.PostRepository;
import com.ptechnology.posthandlingservice.service.PostUploadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class PostSaveToDatabaseTests {

    @Autowired
    private PostUploadService postUploadService;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testSaveToDatabase(){
        Postinfo postinfoTest = new Postinfo(1L, "testfile", "http://localhost:8080", "test description", "http://localhost:8080", "testUser");
        Postinfo p = new Postinfo();
        Mono<Postinfo> testData = postUploadService.saveUploadImageDataToDatabaseTest(postinfoTest);
        testData.flatMap(f -> {
            postinfoTest.setId(f.getId());
            return Mono.just(f);
        }).subscribe();
        StepVerifier.create(testData.log())
                .expectNext(postinfoTest)
                .verifyComplete();
    }

    @Test
    public void testUserRepositorySaveOne(){
        Postinfo postinfoTest = new Postinfo(30L,1L, "testfile", "http://localhost:8080", "test description", "http://localhost:8080", "testUser");

        Mono<Postinfo> p1 = postRepository.save(postinfoTest);

        StepVerifier.create(p1.log())
                .expectNext(postinfoTest)
                .verifyComplete();

        postRepository.delete(postinfoTest);
    }

    @Test
    public void testUserRepositorySaveMore(){
        Postinfo postinfoTest = new Postinfo(30L,1L, "testfile", "http://localhost:8080", "test description", "http://localhost:8080", "testUser");
        Postinfo postinfoTest2 = new Postinfo(31L,1L, "testfile", "http://localhost:8080", "test description", "http://localhost:8080", "testUser");

        Flux<Postinfo> p2 = postRepository.saveAll(Flux.just(postinfoTest, postinfoTest2));

        StepVerifier.create(p2.log())
                .expectNext(postinfoTest)
                .expectNext(postinfoTest2)
                .verifyComplete();

        postRepository.delete(postinfoTest);
    }
}
