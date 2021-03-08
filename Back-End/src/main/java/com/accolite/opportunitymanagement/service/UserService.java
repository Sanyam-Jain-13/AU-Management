package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers() throws Exception;
    public User getUser(String userId) throws Exception;
    public int insert(User user) throws Exception;
}
