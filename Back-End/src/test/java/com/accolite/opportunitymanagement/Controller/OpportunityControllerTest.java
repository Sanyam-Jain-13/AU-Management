package com.accolite.opportunitymanagement.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.accolite.opportunitymanagement.controller.OpportunityController;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;

@WebMvcTest(controllers = OpportunityController.class)
public class OpportunityControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public OpportunityServiceImpl opportunityServiceImpl;

    @Autowired
    public ObjectMapper objectMapper;

    @Before
    private Opportunity setOpportunityObject()
    {
        Opportunity opportunity = new Opportunity();
        opportunity.setDescription("java developer");
        opportunity.setLocation("Mumbai");
        opportunity.setSkills("java");
        opportunity.setUserId("1");
        opportunity.setMinExperience(2);
        opportunity.setDemand(3);
        opportunity.setDate(new Date(System.currentTimeMillis()));
        return opportunity;
    }

    @Test
    public void getAllControllerTest() throws Exception{
        ArrayList<Opportunity> opportunityArrayList = new ArrayList<>();
        Opportunity opportunity = setOpportunityObject();
        opportunityArrayList.add(opportunity);
        //Mockito.when(opportunityServiceImpl.getAllOpportunity()).thenReturn(opportunityArrayList);
        mockMvc.perform(get("/opportunity/getAll")).andExpect(status().isOk());
    }

    @Test
    public void getAllControllerExceptionTest() throws Exception{
        ArrayList<Opportunity> opportunityArrayList = new ArrayList<>();
        Opportunity opportunity = setOpportunityObject();
        opportunityArrayList.add(opportunity);
        //Mockito.when(opportunityServiceImpl.getAllOpportunity()).thenReturn(opportunityArrayList);
        mockMvc.perform(get("/opportunity/getAll")).andExpect(status().isOk());
    }


    @Test
    public void addTest() throws Exception{
        Opportunity opportunity = setOpportunityObject();
        String jsonString = objectMapper.writeValueAsString(opportunity);
        //Mockito.when(opportunityServiceImpl.insert(opportunity)).thenReturn(1);
        mockMvc.perform(post("/opportunity/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception{
        Opportunity opportunity = setOpportunityObject();
        String jsonString = objectMapper.writeValueAsString(opportunity);
        //Mockito.when(opportunityServiceImpl.insert(opportunity)).thenReturn(1);
        mockMvc.perform(post("/opportunity/update/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception{
        int id = 1;
        //Mockito.when(opportunityServiceImpl.delete(id)).thenReturn(1);
        mockMvc.perform(get("/opportunity/delete/".concat(String.valueOf(id)))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }
}