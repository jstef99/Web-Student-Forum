package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.Topic;

import java.util.List;

public interface TopicServiceInterface {
    void saveTopic(Topic topic);

    List<Topic> findAll();

    Topic findById(int id);

    void deleteTopic(Topic topic);

    List<Topic> findByUserId(int id);

    List<Topic> findByUserIdAndThreadId(int id, int threadId);

    List<Topic> findBySubthreadId(int subthreadId);

    void updateTopic(Topic topic);
}
