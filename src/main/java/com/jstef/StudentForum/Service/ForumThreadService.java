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
    public void saveNewThread(ForumThread thread) {
        forumThreadDAO.saveNewThread(thread);
    }

    @Override
    @Transactional
    public void deleteThread(ForumThread thread) {
        forumThreadDAO.deleteThread(thread);
    }

    @Override
    public void deleteById(int id) {
        forumThreadDAO.deleteById(id);
    }
}
