package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/opportunity")
@CrossOrigin("http://localhost:4200")
public class OpportunityController {

    @Autowired
    OpportunityServiceImpl opportunityServiceImpl;

    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/getAll")
    public List<Opportunity> getAllOpportunity() throws Exception {
        List<Opportunity> opportunityList = new ArrayList<>();
        opportunityList = opportunityServiceImpl.getAllOpportunity();
        return opportunityList;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/add")
    public int addOpportunity(@RequestBody Opportunity opportunity)
    {
        return opportunityServiceImpl.insert(opportunity) ;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/update/{id}")
    public int updateOpportunity(@PathVariable("id") int id,@RequestBody Opportunity opportunity)
    {
        return opportunityServiceImpl.update(opportunity,id);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/delete/{id}")
    public int deleteOpportunity(@PathVariable("id") int id)
    {
        return opportunityServiceImpl.delete(id);
    }
}
