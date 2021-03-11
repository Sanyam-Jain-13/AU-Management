package com.accolite.opportunitymanagement.Service;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.mapper.UserMapper;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import com.accolite.opportunitymanagement.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    UserService userService;

    @Test
    public void getAllUsersTest() throws Exception {
        ArrayList<User>userList = new ArrayList<>();
        User userObj = getUserObject();
        userList.add(userObj);
        Mockito.when(userService.getAllUsers()).thenReturn(userList);
        Assert.assertEquals(userList.size(),userService.getAllUsers().size());
    }

    @Test
    public void getUserTest() throws Exception{
        ArrayList<User>userList = new ArrayList<>();
        User userObj = getUserObject();
        userList.add(userObj);
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(),(Object[])Mockito.any(),Mockito.any(UserMapper.class))).thenReturn(userObj);
        User resObj = userServiceImpl.getUser("1");
        Assert.assertEquals(userObj,resObj);
    }

    @Test
    public void insertUserTest() throws Exception {
        User userObj = getUserObject();
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.any()
        )).thenReturn(1);
        int result = userServiceImpl.insert(userObj);
        Assert.assertEquals(1,result);
    }
    public User getUserObject()
    {
        User user = new User();
        user.setUserId("1");
        user.setFirstName("sanyam");
        user.setLastName("jain");
        user.setEmail("sanyamjain@gmail.com");
        user.setPhotoUrl("abcdefghi");
        return user;
    }
}
