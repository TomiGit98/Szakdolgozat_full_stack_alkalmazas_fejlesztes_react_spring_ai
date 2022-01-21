package com.ptechnology.authservice.service;

import com.ptechnology.authservice.domain.Role;
import com.ptechnology.authservice.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
