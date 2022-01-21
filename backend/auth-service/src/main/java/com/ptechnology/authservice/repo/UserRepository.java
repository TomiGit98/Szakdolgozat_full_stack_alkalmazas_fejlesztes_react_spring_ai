package com.ptechnology.authservice.repo;

import com.ptechnology.authservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String name);
    User findUserById(Long id);


}
