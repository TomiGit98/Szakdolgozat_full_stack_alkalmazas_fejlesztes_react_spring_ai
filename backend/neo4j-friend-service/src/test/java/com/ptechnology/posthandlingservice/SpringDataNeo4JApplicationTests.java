package com.ptechnology.posthandlingservice;

import com.ptechnology.posthandlingservice.controller.FriendController;
import com.ptechnology.posthandlingservice.model.User;
import com.ptechnology.posthandlingservice.model.UserDetails;
import com.ptechnology.posthandlingservice.repository.UserRepository;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class SpringDataNeo4JApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGetUserDetailsStatus() throws Exception {
        User user1 = new User(1L, "test@email.com", "test1", "1234");

        userRepository.save(user1);

        mvc.perform(get("/getuserdetails/1"))
                .andExpect(status().isOk());

        userRepository.delete(user1);
    }

}
