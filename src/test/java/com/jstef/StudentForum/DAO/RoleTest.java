package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.RoleDAO;
import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.Entity.Role;
import com.jstef.StudentForum.Entity.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RoleTest {

    @MockBean
    private RoleDAO roleDAO;

    @Before
    public void setUp() {
        Role role = new Role("sample");
        Mockito.when(roleDAO.findRoleByName(role.getName())).thenReturn(role);
    }

    @Test
    public void shouldReturnResultForValidName(){
        Role role = new Role("sample");
        Role desired = roleDAO.findRoleByName(role.getName());
        assertEquals(role,desired);
    }

    @Test
    public void shouldReturnNothingForInvalidName(){
        Role role = new Role("invalid");
        Role desired = roleDAO.findRoleByName(role.getName());
        assertNull(desired);
    }
}