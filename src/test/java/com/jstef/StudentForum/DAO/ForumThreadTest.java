package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.ForumThreadDAO;
import com.jstef.StudentForum.Entity.ForumThread;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ForumThreadTest {

    @Mock
    private ForumThreadDAO forumThreadDAO;

    @Before
    public void setUp() {
        ForumThread forumThread = new ForumThread("sample",1);
        ForumThread anotherThread = new ForumThread("another");
        Mockito.when(forumThreadDAO.deleteById(1)).thenReturn(forumThread);
        Mockito.when(forumThreadDAO.deleteThread(forumThread)).thenReturn(forumThread);
        Mockito.when(forumThreadDAO.findById(1)).thenReturn(forumThread);
        Mockito.when(forumThreadDAO.saveNewThread(anotherThread)).thenReturn(anotherThread);
        Mockito.when(forumThreadDAO.findAll()).thenReturn(Arrays.asList(forumThread,anotherThread));
    }

    @Test
    public void shouldReturnResultForValidId(){
        ForumThread thread = new ForumThread("sample",1);
        ForumThread desired = forumThreadDAO.findById(thread.getId());
        assertEquals(thread,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidId(){
        ForumThread nonValid =  forumThreadDAO.findById(-1);
        assertNull(nonValid);
    }

    @Test
    public void shouldSuccessfullySaveNewThread(){
        ForumThread forumThread = new ForumThread("another");
        ForumThread desired = forumThreadDAO.saveNewThread(forumThread);
        assertEquals(forumThread,desired);
    }

    @Test
    public void shouldSuccessfullyDeleteThreadAndReturn(){
        ForumThread thread = new ForumThread("sample",1);
        ForumThread desired = forumThreadDAO.deleteThread(thread);
        assertEquals(thread,desired);
    }

    @Test
    public void shouldDeleteNothingForNullInput(){
        ForumThread forumThread = null;
        ForumThread desired = forumThreadDAO.deleteThread(forumThread);
        assertNull(desired);
    }

    @Test
    public void shouldReturnListOfResults(){
        List<ForumThread> forumThreads = Arrays.asList(new ForumThread("sample",1),new ForumThread("another"));
        List<ForumThread> desired = forumThreadDAO.findAll();
        assertEquals(forumThreads,desired);
    }


    @Test
    public void shouldCallMockDeleteById(){
        ForumThread forumThread = new ForumThread("sample",1);
        ForumThread returned = forumThreadDAO.deleteById(1);
        assertEquals(forumThread,returned);

    }
}
