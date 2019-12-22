package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.SubthreadDAO;
import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.Entity.Subthread;
import com.jstef.StudentForum.Entity.Token;
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
public class SubthreadTest {

    @MockBean
    private SubthreadDAO subthreadDAO;


    @Before
    public void setUp() {
        Subthread subthread = new Subthread("sample");
    }

    @Test
    public void shouldReturnResultAfterSuccessfulSave(){

    }

    @Test
    public void shouldReturnResultForValidId(){

    }

    @Test
    public void shouldReturnNoResultForInvalidId(){

    }

    @Test
    public void shouldReturnResultForValidThreadId(){

    }

    @Test
    public void shouldReturnNoResultForInvalidThreadId(){

    }

    @Test
    public void shouldReturnAllResults() {

    }

    @Test
    public void shouldDeleteAndReturnResultForValidId () {

    }

    @Test
    public void shouldNotDeleteAndReturnNothingForInvalidId () {

    }
}
