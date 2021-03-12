package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.service.Impl.TrendServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrendController.class)
public class TrendControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public TrendServiceImpl trendServiceImpl;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    public void getLocationTest() throws Exception {
        mockMvc.perform(get("/trends/getLocation")).andExpect(status().isOk());
    }

    @Test
    public void getLocationCountTest() throws Exception {
        mockMvc.perform(get("/trends/getLocationCount")).andExpect(status().isOk());
    }

    @Test
    public void getSkillTest() throws Exception {
        mockMvc.perform(get("/trends/getSkills")).andExpect(status().isOk());
    }

    @Test
    public void getSkillCountTest() throws Exception {
        mockMvc.perform(get("/trends/getSkillsCount")).andExpect(status().isOk());
    }

    @Test
    public void getYearTest() throws Exception {
        mockMvc.perform(get("/trends/getYoY")).andExpect(status().isOk());
    }

    @Test
    public void getYearCountTest() throws Exception {
        mockMvc.perform(get("/trends/getYoYCount")).andExpect(status().isOk());
    }

    @Test
    public void getYearByLocationTest() throws Exception {
        String location = "mumbai";
        mockMvc.perform(get("/trends/getYoY/".concat((location)))).andExpect(status().isOk());
    }

    @Test
    public void getYearByLocationCountTest() throws Exception {
        String location = "mumbai";
        mockMvc.perform(get("/trends/getYoYCount/".concat((location)))).andExpect(status().isOk());
    }
}
