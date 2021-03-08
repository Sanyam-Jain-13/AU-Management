package com.accolite.opportunitymanagement.service.Impl;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.mapper.UserMapper;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() throws Exception {
        String SQL = "select * from User";
        return jdbcTemplate.query(SQL,new UserMapper());
    }

    @Override
    public User getUser(String userId) throws Exception{
        String SQL = "select * from User where userId = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{userId}, new UserMapper());
    }

    @Override
    public int insert(User user) throws Exception{

        int statusCode = -1;
        try {
            String insertSQL = "Insert into User (userId,firstName,lastName,email,photoUrl) values (?,?,?,?,?)";
            statusCode = jdbcTemplate.update(insertSQL, user.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhotoUrl());
        }
        catch (Exception e) {
            System.out.println(e);
            return statusCode;
        }
        return statusCode;
    }

}
