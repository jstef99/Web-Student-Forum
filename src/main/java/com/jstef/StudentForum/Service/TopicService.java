package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.DataAccessObject.TopicDAO;
import com.jstef.StudentForum.Entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicService implements TopicServiceInterface {
    @Autowired
    TopicDAO topicDAO;

    @Override
    @Transactional
    public Topic saveTopic(Topic topic) {
        return topicDAO.saveTopic(topic);
    }

    @Override
    @Transactional
    public List<Topic> findAll() {
        return topicDAO.findAll();
    }

    @Override
    @Transactional
    public Topic findById(int id) {
        return topicDAO.findById(id);
    }

    @Override
    @Transactional
    public Topic deleteTopic(Topic topic) {
        return topicDAO.deleteTopic(topic);
    }

    @Override
    @Transactional
    public List<Topic> findByUserId(int id) {
        return topicDAO.findByUserId(id);
    }

    @Override
    public List<Topic> findByUserIdAndThreadId(int id, int threadId) {
        return topicDAO.findByUserIdAndThreadId(id,threadId);
    }

    @Override
    public List<Topic> findBySubthreadId(int subthreadId) {
        return topicDAO.findBySubthreadId(subthreadId);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        return topicDAO.updateTopic(topic);
    }
}
