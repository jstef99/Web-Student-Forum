package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.DataAccessObject.TopicDAO;
import com.jstef.StudentForum.Entity.Token;
import com.jstef.StudentForum.Entity.Topic;
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
public class TopicTest {

    @MockBean
    private TopicDAO topicDAO;

    @Before
    public void setUp() {
        Topic topic = new Topic(1,2,3);
    }

    @Test
    public void shouldReturnTopicWhenSuccessfulSave(){

    }

    @Test
    public void shouldReturnListOfAllInstances(){

    }

    @Test
    public void shouldReturnResultForValidId(){

    }

    @Test
    public void shouldReturnNoResultForInvalidId(){

    }

    @Test
    public void shouldReturnTopicWhenSuccessfulDelete(){

    }

    @Test
    public void shouldFindUserForValidId(){

    }

    @Test
    public void shouldReturnNoUserForInvalidId(){

    }

    @Test
    public void shouldReturnTopicsForValidUserAndThreadId(){

    }

    @Test
    public void shouldReturnNoTopicsForInvalidUserId(){

    }

    @Test
    public void shouldReturnNoTopicsForInvalidThreadId(){

    }

    @Test
    public void shouldReturnAllResultsForValidSubthreadId(){

    }

    @Test
    public void shouldReturnNoResultsForInvalidSubthreadId(){

    }

    @Test
    public void showReturnTopicAfterSuccessfulUpdate(){

    }
 }