package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.ForumThread;

import java.util.List;

public interface ForumThreadServiceInterface {
    public List<ForumThread> findAll();
    public ForumThread findById(int Id);
    void saveNewThread(ForumThread thread);
    void deleteThread(ForumThread thread);

    void deleteById(int id);
}
