package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.AllowedEmailDAO;
import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.Entity.AllowedEmail;
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
public class AllowedEmailTest {

    @MockBean
    private AllowedEmailDAO allowedEmailDAO;

    @Before
    public void setUp() {
        AllowedEmail allowedEmail = new AllowedEmail("sample",false);
        Mockito.when(allowedEmailDAO.findByName(allowedEmail.getName()))
                .thenReturn(allowedEmail);
        Mockito.when(allowedEmailDAO.updateEmail(allowedEmail)).thenReturn(allowedEmail);
    }

    @Test
    public void shouldReturnEmailForValidName(){
        AllowedEmail email = new AllowedEmail("sample",false);
        AllowedEmail desired = allowedEmailDAO.findByName(email.getName());
        assertEquals(email,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidName(){
        AllowedEmail desired = allowedEmailDAO.findByName("non-existing");
        assertNull(desired);
    }

    @Test
    public void shouldReturnTokenWhenSuccessfulUpdate(){
        AllowedEmail email = new AllowedEmail("sample", false);
        AllowedEmail returned = allowedEmailDAO.updateEmail(email);
        assertEquals(email,returned);
    }
}