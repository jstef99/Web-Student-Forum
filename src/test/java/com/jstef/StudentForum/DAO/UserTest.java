package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.UserDAO;
import com.jstef.StudentForum.Entity.Token;
import com.jstef.StudentForum.Entity.Topic;
import com.jstef.StudentForum.Entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserTest {

    @MockBean
    private UserDAO userDAO;

    @Before
    public void setUp() {
        User user = new User();
    }

    @Test
    public void shouldReturnUserForValidUsername(){

    }

    @Test
    public void shouldReturnNoResultForInvalidUsername(){

    }

    @Test
    public void shouldReturnListOfAllResults(){

    }

    @Test
    public void shouldReturnUserAfterSuccessfulSave(){

    }

    @Test
    public void shouldReturnUserAfterSuccessfulUpdate(){

    }

    @Test
    public void shouldReturnUserForValidId(){

    }

    @Test
    public void shouldReturnNoResultForInvalidId(){

    }

    @Test
    public void shouldReturnBiggestId(){

    }

    @Test
    public void shouldReturnAllSignedUpUsersForValidTopic(){

    }

    @Test
    public void shouldReturnNoResultForInvalidTopic(){

    }
}