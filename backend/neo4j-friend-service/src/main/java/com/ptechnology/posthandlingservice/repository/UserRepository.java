package com.ptechnology.posthandlingservice.repository;


import com.ptechnology.posthandlingservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    User findByUseridEquals(Long userid);
    List<User> findByFriendsUsername(String username);
}
