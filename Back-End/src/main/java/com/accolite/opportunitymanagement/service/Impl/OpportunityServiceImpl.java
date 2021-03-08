package com.accolite.opportunitymanagement.service.Impl;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository("OpportunityService")
@Transactional
public class OpportunityServiceImpl implements OpportunityService  {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Opportunity> getAllOpportunity() throws Exception {
        String SQL = "select * from Opportunity";
        return jdbcTemplate.query(SQL,new OpportunityMapper());
    }

    @Override
    public int insert(Opportunity opportunity) {
        String insertSQL = "insert into Opportunity (description,location,skills,userId,minExperience,demand,date) values (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(insertSQL, opportunity.getDescription(),
                opportunity.getLocation(),
                opportunity.getSkills(),
                opportunity.getUserId(),
                opportunity.getMinExperience(),
                opportunity.getDemand(),
                opportunity.getDate());
    }

    @Override
    public int update(Opportunity opportunity,int id) {
        String SQL = "UPDATE opportunity SET description=?, location=?, skills=?, minExperience=?, demand=? , date = ? WHERE id=?";
        return jdbcTemplate.update(SQL, opportunity.getDescription(),
                opportunity.getLocation(),
                opportunity.getSkills(),
                opportunity.getMinExperience(),
                opportunity.getDemand(),
                opportunity.getDate(),
                id);
    }

    @Override
    public int delete(int id) {
        String SQL = "DELETE FROM opportunity WHERE id =?";
        return jdbcTemplate.update(SQL,new Object[]{id});
    }
}
