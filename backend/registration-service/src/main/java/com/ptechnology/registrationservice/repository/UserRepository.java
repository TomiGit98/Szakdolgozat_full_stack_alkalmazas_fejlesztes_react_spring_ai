package com.ptechnology.registrationservice.repository;

import com.ptechnology.registrationservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.ready = ?1 where u.id = ?2")
    void setReadyByUserId(Boolean ready, Long userId);
}
