package com.ptechnology.registrationservice.repository;

import com.ptechnology.registrationservice.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    User findUserByEmail(String email);
}
