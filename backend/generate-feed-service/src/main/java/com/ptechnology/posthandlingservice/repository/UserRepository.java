package com.ptechnology.posthandlingservice.repository;

import com.ptechnology.posthandlingservice.model.Userpostinfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<Userpostinfo, String> {

    Mono<Userpostinfo> findByUseridEquals(Long id);
    Mono<Boolean> existsUserpostinfoByUserid(Long id);
    //Mono<Long> countByUserid(Long id);
    boolean countByUserid(Long id);

}
