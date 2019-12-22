package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.DataAccessObject.ForumThreadDAO;
import com.jstef.StudentForum.Entity.ForumThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ForumThreadService implements ForumThreadServiceInterface {
    @Autowired
    ForumThreadDAO forumThreadDAO;

    @Override
    @Transactional
    public List<ForumThread> findAll() {
        return forumThreadDAO.findAll();
    }

    @Override
    @Transactional
    public ForumThread findById(int Id) {
        return forumThreadDAO.findById(Id);
    }

    @Override
    @Transactional
    public ForumThread saveNewThread(ForumThread thread) {
        return forumThreadDAO.saveNewThread(thread);
    }

    @Override
    @Transactional
    public ForumThread deleteThread(ForumThread thread) {
        return forumThreadDAO.deleteThread(thread);
    }

    @Override
    public ForumThread deleteById(int id) {
        return forumThreadDAO.deleteById(id);
    }
}
