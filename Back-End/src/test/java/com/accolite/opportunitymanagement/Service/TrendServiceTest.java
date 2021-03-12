package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.service.Impl.TrendServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@SpringBootTest
public class TrendServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    TrendServiceImpl trendService;

    @Test
    public void trendLocation(){

        List<String> list = new ArrayList<>();
        list.add("DELHI");

        List<Map<String,Object>> rows = new ArrayList();
        rows.add(new HashMap(){
            {put("location","delhi");}
        });

        Mockito.when(jdbcTemplate.queryForList(
                Mockito.anyString())).thenReturn(rows);
        Assert.assertEquals(trendService.getByLocation(),list);
    }

    @Test
    public void trendLocationCountTest()
    {
        List<Long> list = new ArrayList<>();
        list.add(2L);

        List<Map<String,Object>> rows = new ArrayList();
        rows.add(new HashMap(){
            {put("count(*)",1L); put("location","delhi");}
        });
        rows.add(new HashMap(){
            {put("count(*)",1L); put("location","delhi");}
        });
        Mockito.when(jdbcTemplate.queryForList(
                Mockito.anyString())).thenReturn(rows);
        Assert.assertEquals(trendService.getByLocationCount(),list);
    }

    @Test
    public void trendSkill(){

        List<String> list = new ArrayList<>();
        list.add("Java");

        List<Map<String,Object>> rows = new ArrayList();
        rows.add(new HashMap(){
            {put("skills","Java");}
        });
        rows.add(new HashMap(){
            {put("skills","Java");}
        });

        Mockito.when(jdbcTemplate.queryForList(
                Mockito.anyString())).thenReturn(rows);
        Assert.assertEquals(trendService.getBySkill(),list);
    }

    @Test
    public void trendSkillCountTest()
    {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        List<Map<String,Object>> rows = new ArrayList();
        rows.add(new HashMap(){
            {put("skills","Java");}
        });
        rows.add(new HashMap(){
            {put("skills","Java");}
        });
        Mockito.when(jdbcTemplate.queryForList(
                Mockito.anyString())).thenReturn(rows);
        Assert.assertEquals(trendService.getBySkillCount(),list);
    }

    @Test
    public void trendGetYoYTest()
    {
        List<String> list = new ArrayList<>();
        list.add("2021");


        List<Map<String,Object>> rows = new ArrayList<>();
        rows.add(new HashMap(){
            {put("date","2021");}
        });

        rows.add(new HashMap(){
            {put("date","2021");}
        });

        Mockito.when(jdbcTemplate.queryForList(
                Mockito.anyString())).thenReturn(rows);
        Assert.assertEquals(list,trendService.getByYoY());
    }


    @Test
    public void trendYearCountTest(){

        List<Integer> list = new ArrayList<>();
        list.add(2);


        List<Map<String,Object>> rows = new ArrayList<>();
        rows.add(new HashMap(){
            {put("date","2021"); put("demand",1);}
        });

        rows.add(new HashMap(){
            {put("date","2021"); put("demand",1);}
        });

        Mockito.when(jdbcTemplate.queryForList(
                Mockito.anyString())).thenReturn(rows);
        Assert.assertEquals(trendService.getByYoYCount(),list);

    }
}
