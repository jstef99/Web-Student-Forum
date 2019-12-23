package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.SubthreadDAO;
import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.Entity.Subthread;
import com.jstef.StudentForum.Entity.Token;
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
public class SubthreadTest {

    @MockBean
    private SubthreadDAO subthreadDAO;


    @Before
    public void setUp() {
        Subthread subthread = new Subthread("sample",1);
        Subthread subthread2 = new Subthread("another",2);
        Mockito.when(subthreadDAO.findById(1)).thenReturn(subthread);
        Mockito.when(subthreadDAO.saveNewSubthread(subthread)).thenReturn(subthread);
        Mockito.when(subthreadDAO.findAll()).thenReturn(Arrays.asList(subthread,subthread2));
    }

    @Test
    public void shouldReturnResultAfterSuccessfulSave(){
        Subthread subthread = new Subthread("sample",1);
        Subthread desired = subthreadDAO.saveNewSubthread(subthread);
        assertEquals(subthread,desired);
    }

    @Test
    public void shouldReturnResultForValidId(){
        Subthread subthread = new Subthread("sample",1);
        Subthread desired = subthreadDAO.findById(subthread.getId());
        assertEquals(subthread,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidId(){
        Subthread desired = subthreadDAO.findById(2);
        assertNull(desired);
    }

    @Test
    public void shouldReturnAllResults() {
        List<Subthread> subthreads = Arrays.asList(new Subthread("sample",1),new Subthread("another",2));
        List<Subthread> desired = subthreadDAO.findAll();
        assertEquals(subthreads,desired);
    }
}
