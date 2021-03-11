package com.accolite.opportunitymanagement.Service;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;
import com.accolite.opportunitymanagement.service.OpportunityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpportunityServiceTest {

    @InjectMocks
    private OpportunityServiceImpl opportunityServiceImpl;

    @Mock
    private OpportunityService opportunityService;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void getAllTest() throws Exception {

        ArrayList<Opportunity>OpportunityList = new ArrayList<>();
        Opportunity opportunity = opportunityObject();
        OpportunityList.add(opportunity);
        Mockito.when(opportunityService.getAllOpportunity()).thenReturn(OpportunityList);
        Assert.assertEquals(OpportunityList.size(),opportunityService.getAllOpportunity().size());
    }

    @Test
    public void insertTest() throws Exception {
        Opportunity opportunity = opportunityObject();
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.any()
        )).thenReturn(1);
        //Mockito.when(opportunityService.insert(opportunity)).thenReturn(1);
        int resultVal = opportunityServiceImpl.insert(opportunity);
        Assert.assertEquals(1,resultVal);
    }

    @Test
    public void updateTest() throws Exception {
        Opportunity opportunity = opportunityObject();
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.any()
        )).thenReturn(1);
        int resultVal = opportunityServiceImpl.update(opportunity,1);
        Assert.assertEquals(1,resultVal);
    }

    @Test
    public void deleteTest() throws Exception {
        int expectedVal = 1;
        int id = 1;
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.any()
        )).thenReturn(expectedVal);
        int resultVal = opportunityServiceImpl.delete(id);
        Assert.assertEquals(expectedVal,resultVal);
    }

    public Opportunity opportunityObject() {
        Opportunity opportunity = new Opportunity();
        opportunity.setId(1);
        opportunity.setDescription("Web Developer");
        opportunity.setLocation("Delhi");
        opportunity.setSkills("Html");
        opportunity.setUserId("2");
        opportunity.setMinExperience(1);
        opportunity.setDemand(1);
        opportunity.setDate(new Date(System.currentTimeMillis()));
        return opportunity;
    }


}
