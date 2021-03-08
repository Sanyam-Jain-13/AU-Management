package com.accolite.opportunitymanagement.mapper;

import com.accolite.opportunitymanagement.model.Opportunity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OpportunityMapper implements RowMapper<Opportunity> {

    public Opportunity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Opportunity opportunity = new Opportunity();
        opportunity.setId(rs.getInt("Id"));
        opportunity.setDescription(rs.getString("description"));
        opportunity.setLocation(rs.getString("location"));
        opportunity.setSkills(rs.getString("skills"));
        opportunity.setUserId(rs.getInt("userId"));
        opportunity.setMinExperience(rs.getInt("minExperience"));
        opportunity.setDemand(rs.getInt("demand"));
        opportunity.setDate(rs.getDate("date"));
        return opportunity;
    }
}
