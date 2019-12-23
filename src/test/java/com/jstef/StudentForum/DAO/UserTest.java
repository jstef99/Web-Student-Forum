package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.UserDAO;
import com.jstef.StudentForum.Entity.Token;
import com.jstef.StudentForum.Entity.Topic;
import com.jstef.StudentForum.Entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserTest {

    @MockBean
    private UserDAO userDAO;

    @Before
    public void setUp() {
        User user = new User("uname","pass","gmail");
        User user2 = new User(1,"uname2","pass2","gmail2");
        User user3 = new User(10,"uname2","pass2","gmail2");
        Mockito.when(userDAO.findAll()).thenReturn(Arrays.asList(user,user2));
        Mockito.when(userDAO.findByUserName("uname")).thenReturn(user);
        Mockito.when(userDAO.saveNewUser(user)).thenReturn(user);
        Mockito.when(userDAO.updateUser(user)).thenReturn(user);
        Mockito.when(userDAO.findBiggestId()).thenReturn(user3.getId());
        Mockito.when(userDAO.findById(1)).thenReturn(user2);
    }

    @Test
    public void shouldReturnUserForValidUsername(){
        User user = new User("uname","pass","gmail");
        User desired = userDAO.findByUserName(user.getUsername());
        assertEquals(user,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidUsername(){
        User user = userDAO.findByUserName("non-existing");
        assertNull(user);
    }

    @Test
    public void shouldReturnListOfAllResults(){
        List<User>users = Arrays.asList(new User("uname","pass","gmail"),new User(1,"uname2","pass2","gmail2"));
        List<User>desired = userDAO.findAll();
        assertEquals(users,desired);
    }

    @Test
    public void shouldReturnUserAfterSuccessfulSave(){
        User user = new User("uname","pass","gmail");
        User desired = userDAO.saveNewUser(user);
        assertEquals(user,desired);
    }

    @Test
    public void shouldReturnUserAfterSuccessfulUpdate(){
        User user = new User("uname","pass","gmail");
        User desired = userDAO.updateUser(user);
        assertEquals(user,desired);
    }

    @Test
    public void shouldReturnUserForValidId(){
        User user = new User(1,"uname2","pass2","gmail2");
        User desired = userDAO.findById(1);
        assertEquals(user,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidId(){
        User user = userDAO.findById(-1);
        assertNull(user);
    }

    @Test
    public void shouldReturnBiggestId(){
        User user = new User(10,"uname","pass","gmail");
        int val = userDAO.findBiggestId();
        assertEquals(user.getId(),val);
    }

}