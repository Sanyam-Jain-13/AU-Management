package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.model.Opportunity;

import java.util.List;

public interface OpportunityService {

    public List<Opportunity> getAllOpportunity() throws Exception;

    public int insert(Opportunity opportunity) throws Exception;

    public int update(Opportunity opportunity,int id) throws Exception;

    public int delete(int id) throws Exception;

}
