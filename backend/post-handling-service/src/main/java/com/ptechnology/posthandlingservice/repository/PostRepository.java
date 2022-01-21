package com.ptechnology.posthandlingservice.repository;

import com.ptechnology.posthandlingservice.model.Postinfo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveCrudRepository<Postinfo, Long> {

    @Query("SELECT * FROM postinfo WHERE userid = :userid")
    Flux<Postinfo> findPostinfoByUserid(String userid);
}
