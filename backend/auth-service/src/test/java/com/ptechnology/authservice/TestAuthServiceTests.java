package com.ptechnology.authservice;

import com.ptechnology.authservice.api.UserResource;
import com.ptechnology.authservice.domain.Role;
import com.ptechnology.authservice.domain.User;
import com.ptechnology.authservice.repo.RoleRepository;
import com.ptechnology.authservice.repo.UserRepository;
import com.ptechnology.authservice.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.Assert;

import static org.apache.http.client.methods.RequestBuilder.get;
import static org.apache.http.client.methods.RequestBuilder.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestAuthServiceTests {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userService).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(roleRepository).isNotNull();
    }

    @Test
    public void saveUserTest() {
        User userTest = new User(1L, "testmail@test.com", "testusername", "testpassword", true, null);
        userService.saveUser(userTest);
        Assert.notNull(userRepository.findByEmail("testmail@test.com"));
        userRepository.delete(userTest);
    }

    @Test
    public void saveRoleTest() {
        Role roleTest = new Role(1L, "ADMIN");
        userService.saveRole(roleTest);
        Assert.notNull(roleRepository.findByName("ADMIN"));
        roleRepository.delete(roleTest);
    }
}
