package com.ptechnology.posthandlingservice;

import com.ptechnology.posthandlingservice.model.User;
import com.ptechnology.posthandlingservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SpringBootTestInit {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void testSaveAndLoadUserFromDatabase() {
        User user1 = new User(1L, "test@email.com", "test1", "1234");
        User user2 = new User(2L, "test2@email.com", "test2", "1234");

        userRepository.save(user1);
        userRepository.save(user2);

        User res1 = userRepository.findByUseridEquals(1L);
        User res2 = userRepository.findByUseridEquals(2L);

        System.out.println(res1);
        System.out.println(res2);

        Assert.assertEquals(user1.getEmail(), res1.getEmail());
        Assert.assertEquals(user2.getEmail(), res2.getEmail());

        userRepository.delete(res1);
        userRepository.delete(res2);
    }
}
