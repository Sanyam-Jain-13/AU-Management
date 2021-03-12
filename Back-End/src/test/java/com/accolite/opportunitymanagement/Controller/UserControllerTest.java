package com.accolite.opportunitymanagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.accolite.opportunitymanagement.controller.UserController;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;


@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public UserServiceImpl userServiceImpl;

    @Test
    public void getAllUsers() throws Exception{
        ArrayList<User> userList = new ArrayList<>();
        User user = new User("1","sanyam","jain","sanyam@gmail.com","abcde");
        userList.add(user);
        //Mockito.when(userServiceImpl.getAllUsers()).thenReturn(userList);
        mockMvc.perform(get("/user/getAllUsers")).andExpect(status().isOk());
    }

    @Test
    public void getUser() throws Exception{
        String id = "1";
        User user = new User("1","sanyam","jain","sanyam@gmail.com","abcde");
        //Mockito.when(userServiceImpl.getUser(id)).thenReturn(user);
        mockMvc.perform(get("/user/getUser/"+id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(id)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    public void addUserTest() throws Exception {
        User user = new User("1","sanyam","jain","sanyam@gmail.com","abcde");
        String jsonString = objectMapper.writeValueAsString(user);
        //Mockito.when(userServiceImpl.insert(user)).thenReturn(1);
        mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

}
