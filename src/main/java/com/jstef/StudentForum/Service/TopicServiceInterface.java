package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.Topic;

import java.util.List;

public interface TopicServiceInterface {
    Topic saveTopic(Topic topic);

    List<Topic> findAll();

    Topic findById(int id);

    Topic deleteTopic(Topic topic);

    List<Topic> findByUserId(int id);

    List<Topic> findByUserIdAndThreadId(int id, int threadId);

    List<Topic> findBySubthreadId(int subthreadId);

    Topic updateTopic(Topic topic);
}
