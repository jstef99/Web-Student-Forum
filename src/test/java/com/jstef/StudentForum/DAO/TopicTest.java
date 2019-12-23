package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.DataAccessObject.TopicDAO;
import com.jstef.StudentForum.Entity.Token;
import com.jstef.StudentForum.Entity.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TopicTest {

    @MockBean
    private TopicDAO topicDAO;

    @Before
    public void setUp() {
        Topic topic = new Topic(1,2,3);
        Topic topic2 = new Topic(4,5,6);
        Topic topic3 = new Topic(3,2,6);
        Mockito.when(topicDAO.saveTopic(topic)).thenReturn(topic);
        Mockito.when(topicDAO.findAll()).thenReturn(Arrays.asList(topic,topic2));
        Mockito.when(topicDAO.findById(1)).thenReturn(topic);
        Mockito.when(topicDAO.deleteTopic(topic)).thenReturn(topic);
        Mockito.when(topicDAO.findByUserId(2)).thenReturn(Arrays.asList(topic,topic3));
        Mockito.when(topicDAO.findByUserIdAndThreadId(1,3)).thenReturn(Arrays.asList(topic));
        Mockito.when(topicDAO.updateTopic(topic)).thenReturn(topic);
    }

    @Test
    public void shouldReturnTopicWhenSuccessfulSave(){
        Topic topic = new Topic(1,2,3);
        Topic desired = topicDAO.saveTopic(topic);
        assertEquals(topic,desired);
    }

    @Test
    public void shouldReturnListOfAllInstances(){
        List<Topic>topics = Arrays.asList(new Topic(1,2,3), new Topic(4,5,6));
        List<Topic> desired = topicDAO.findAll();
        assertEquals(topics,desired);
    }

    @Test
    public void shouldReturnResultForValidId(){
        Topic topic = new Topic(1,2,3);
        Topic desired = topicDAO.findById(topic.getId());
        assertEquals(topic,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidId(){
        Topic desired = topicDAO.findById(-1);
        assertNull(desired);
    }

    @Test
    public void shouldReturnTopicWhenSuccessfulDelete(){
        Topic topic = new Topic(1,2,3);
        Topic desired = topicDAO.deleteTopic(topic);
        assertEquals(desired,topic);
    }

    @Test
    public void shouldFindAllTopicsForUserValidId(){
        List<Topic>topics = Arrays.asList(new Topic(1,2,3), new Topic(3,2,6));
        List<Topic>desired = topicDAO.findByUserId(2);
        assertEquals(topics,desired);
    }

    @Test
    public void shouldReturnNoTopicsForUserInvalidId(){
        List<Topic> desired = topicDAO.findByUserId(-1);
        assertTrue(desired.size()==0);
    }

    @Test
    public void shouldReturnTopicsForValidUserAndThreadId(){
        List<Topic>topics = Arrays.asList(new Topic(1,2,3));
        List<Topic>desired = topicDAO.findByUserIdAndThreadId(1,3);
        assertEquals(topics,desired);
    }

    @Test
    public void shouldReturnNoTopicsForInvalidUserId(){
        List<Topic>desired = topicDAO.findByUserIdAndThreadId(-1,3);
        assertTrue(desired.size()==0);
    }

    @Test
    public void shouldReturnNoTopicsForInvalidThreadId(){
        List<Topic> desired = topicDAO.findByUserIdAndThreadId(1,-1);
        assertTrue(desired.size()==0);
    }

    @Test
    public void shouldReturnTopicAfterSuccessfulUpdate(){
        Topic topic = new Topic(1,2,3);
        Topic desired= topicDAO.updateTopic(topic);
        assertEquals(topic,desired);
    }
 }