package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.ForumThread;

import java.util.List;

public interface ForumThreadServiceInterface {
    public List<ForumThread> findAll();
    public ForumThread findById(int Id);
    ForumThread saveNewThread(ForumThread thread);
    ForumThread deleteThread(ForumThread thread);
    ForumThread deleteById(int id);
}
