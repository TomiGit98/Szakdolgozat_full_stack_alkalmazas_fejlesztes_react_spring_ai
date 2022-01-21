package com.ptechnology.aiauthservice.repository;

import com.ptechnology.aiauthservice.model.Authinfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AuthInfoRepository extends ReactiveCrudRepository<Authinfo, Long> {

}
